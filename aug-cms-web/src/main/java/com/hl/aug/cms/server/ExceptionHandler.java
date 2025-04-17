package com.hl.aug.cms.server;

import com.alibaba.fastjson.JSONObject;
import com.hl.aug.cms.common.enums.CommonErrorEnum;
import com.hl.aug.cms.common.enums.LogLevelEnum;
import com.hl.aug.cms.common.enums.ResultCodeEnum;
import com.hl.aug.cms.common.exception.CommonErrorException;
import com.hl.aug.cms.common.util.LoggerUtil;
import com.hl.aug.cms.common.util.WebMvcUtil;
import com.hl.aug.cms.response.WinSportBaseResp;
import com.hl.aug.cms.util.ErrorCodeEnums;
import com.hl.aug.cms.util.IException;
import com.hl.aug.cms.util.ResultUtil;
import com.hl.aug.cms.util.ResultsFactory;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ExceptionHandler extends DefaultHandlerExceptionResolver {

    private static final Logger loggerError = LoggerUtil.COMMON_ERROR;

    private static final Logger logger = LoggerUtil.COMMON_DEFAULT;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return dealException(request, response, ex);
    }

    private ModelAndView dealException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        boolean showErrorMsg = true;
        boolean showStackTrace = true;
        boolean isClientAbort = false;
        Object result = ResultUtil.failed(CommonErrorEnum.SYSTEM_ERROR);
        try {
            if (ex instanceof HttpRequestMethodNotSupportedException) {
                result = ResultUtil.failed(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "系统繁忙,请稍后再试");
                AccessLog.printLog(request);
                showErrorMsg = false;
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                result = ResultUtil.failed(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "系统繁忙,请稍后再试");
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                result = ResultUtil.failed(HttpServletResponse.SC_NOT_ACCEPTABLE, "系统繁忙,请稍后再试");
            } else if ((ex instanceof MissingPathVariableException) ||
                    (ex instanceof ConversionNotSupportedException) ||
                    (ex instanceof HttpMessageNotWritableException)) {
                result = ResultUtil.failed(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统繁忙,请稍后再试");
            } else if (ex instanceof MissingServletRequestParameterException) {
                result = ResultUtil.failed(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                showErrorMsg = false;
            } else if (ex instanceof IllegalArgumentException) {
                result = WinSportBaseResp.error(Integer.parseInt(ErrorCodeEnums.VALIDATOR_CHECK_ERROR.getErrorCode()), ex.getMessage());
            } else if (ex instanceof HttpMessageNotReadableException) {
                result = ResultUtil.failed(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                showErrorMsg = false;
            } else if (ex instanceof MethodArgumentNotValidException) {
                BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
                if (bindingResult.hasErrors()) {
                    // 获取所有字段参数不匹配的参数集合
                    List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                    if (CollectionUtils.isNotEmpty(fieldErrorList)) {
                        result =
                                ResultsFactory.buildError(HttpServletResponse.SC_BAD_REQUEST, fieldErrorList.get(0).getDefaultMessage());
                    }
                }
                showErrorMsg = false;
                showStackTrace = false;
            } else if ((ex instanceof ServletRequestBindingException) ||
                    (ex instanceof TypeMismatchException) ||
                    (ex instanceof MissingServletRequestPartException)) {
                result = ResultUtil.failed(HttpServletResponse.SC_BAD_REQUEST, "系统繁忙,请稍后再试");
            } else if (ex instanceof BindException) {
                BindException e = (BindException) ex;
                result = ResultUtil.failed(HttpServletResponse.SC_BAD_REQUEST, e.getBindingResult().getAllErrors()
                        .stream()
                        .min(Comparator.comparing(
                                x -> FieldError.class.isAssignableFrom(x.getClass()) ? ((FieldError) x).getField() : x.getCode()
                        ))
                        .get()
                        .getDefaultMessage());
                showErrorMsg = false;
                showStackTrace = false;
            } else if (ex instanceof ConstraintViolationException) {
                ConstraintViolationException e = (ConstraintViolationException) ex;
                if (CollectionUtils.isNotEmpty(e.getConstraintViolations())) {
                    String defaultMessage =
                            e.getConstraintViolations().stream()
                                    .findFirst()
                                    .map(ConstraintViolation::getMessage)
                                    .orElse("");
                    result = this.convert(defaultMessage);
                    if (Objects.isNull(result)) {
                        result = WinSportBaseResp.error(ResultCodeEnum.SYS_ERROR.getCode(), "参数有误");
                    }
                } else {
                    result = WinSportBaseResp.error(ResultCodeEnum.SYS_ERROR.getCode(), "参数有误");
                }
                showErrorMsg = false;
            } else if (ex instanceof NoHandlerFoundException) {
                result = ResultUtil.failed(HttpServletResponse.SC_NOT_FOUND, "系统繁忙,请稍后再试");
            } else if (ex instanceof AsyncRequestTimeoutException) {
                result = ResultUtil.failed(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "系统繁忙,请稍后再试");
            } else if (ex instanceof org.springframework.dao.DuplicateKeyException) {
                result = ResultUtil.failed(CommonErrorEnum.SYSTEM_ERROR.getCode(), "数据重复，请刷新重试");
            } else if (ex instanceof CommonErrorException) {
                CommonErrorException ex1 = (CommonErrorException) ex;
                CommonErrorEnum errorEnum = CommonErrorEnum.getByCode(ex1.getCode());
                String message = Objects.equals(CommonErrorEnum.BIZ_RULE_CHECK_ERROR, errorEnum) ?
                        ex1.getMessage() : errorEnum.getErrorMsg();
                result = ResultUtil.failed(errorEnum, message);
                showErrorMsg = LogLevelEnum.ERROR == errorEnum.getLogLevel();
                showStackTrace = LogLevelEnum.ERROR == errorEnum.getLogLevel();
            }
            // ClientAbortException 异常打印warn日志
            else if (ex instanceof ClientAbortException) {
                isClientAbort = true;
            } else if (ex instanceof IException) {
                result = ResultUtil.failed(CommonErrorEnum.SYSTEM_ERROR.getCode(), ex.getMessage());
                // 自定义错误不打印日志
                showErrorMsg = false;
                showStackTrace = false;
            }

            // 开始日志打印
            if (isClientAbort) {
                LoggerUtil.warn(logger, "发生clientAbortException");
                return new ModelAndView();
            } else if (showErrorMsg) {
                if (showStackTrace) {
                    LoggerUtil.error(loggerError, ex);
                } else {
                    LoggerUtil.error(loggerError, ex.getMessage());
                }
            } else {
                if (showStackTrace) {
                    LoggerUtil.warn(logger, ex);
                } else {
                    LoggerUtil.warn(logger, ex.getMessage());
                }
            }

            // 设置返回结果
            WebMvcUtil.printResult(response, result);
        } catch (Throwable th) {
            LoggerUtil.error(loggerError, th, "[ExceptionHandler]处理异常");
        }
        return new ModelAndView();
    }

    WinSportBaseResp<Object> convert(String strJson) {
        if (StringUtils.isBlank(strJson)) {
            return null;
        }

        try {
            return JSONObject.parseObject(strJson, WinSportBaseResp.class);
        } catch (Exception e) {
            return null;
        }
    }
}
