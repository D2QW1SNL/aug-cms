package com.hl.aug.cms.server;

import com.alibaba.fastjson.JSONObject;
import com.hl.aug.cms.common.enums.DateTimeFormatterEnum;
import com.hl.aug.cms.common.enums.ResultCodeEnum;
import com.hl.aug.cms.common.enums.WebHeaderEnum;
import com.hl.aug.cms.common.util.LocalDateTimeUtil;
import com.hl.aug.cms.common.util.LoggerUtil;
import com.hl.aug.cms.common.util.WebMvcUtil;
import com.hl.aug.cms.dto.MimeHeadersDTO;
import com.hl.aug.cms.response.WinSportBaseResp;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerUtil.COMMON_DEFAULT;

    public static final Logger access_log = LoggerUtil.INTERCEPTOR;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new ExceptionHandler());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new ExceptionHandler());
    }

    public class LoginInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            if (!(handler instanceof HandlerMethod) || !(request instanceof BodyRequestWrapper || request instanceof StandardMultipartHttpServletRequest)) {
                WebMvcUtil.printResult(response, WinSportBaseResp.error(ResultCodeEnum.SYS_ERROR));
                return false;
            }

            BodyRequestWrapper requestWrapper;
            if (request instanceof StandardMultipartHttpServletRequest) {
                requestWrapper = (BodyRequestWrapper) ((StandardMultipartHttpServletRequest) request).getRequest();
            } else {
                requestWrapper = (BodyRequestWrapper) request;
            }

            // 清空线程上下文
            ThreadContext.clearMap();
            ThreadLocalUtil.clear();

            ThreadContext.put(WebHeaderEnum.TRACE_ID.getCode(), requestWrapper.getTraceId());
            response.addHeader(WebHeaderEnum.TRACE_ID.getCode(), ThreadContext.get(WebHeaderEnum.TRACE_ID.getCode()));

            // 打印日志
            MimeHeadersDTO mimeHeadersDTO = AccessLog.printLog(requestWrapper);
            ThreadLocalUtil.setUserLogin(buildUserLoginDTO(mimeHeadersDTO));

            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                    Exception ex) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("traceid", ThreadContext.get(WebHeaderEnum.TRACE_ID.getCode()));
            jsonObject.put("serverTime", LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnum.FORMAT_YMD_HMSsss));
            LoggerUtil.info(access_log, jsonObject);
            ThreadContext.clearMap();
            ThreadLocalUtil.clear();
        }
    }

    /**
     * 构建用户信息
     *
     * @param headersDTO
     * @return
     */
    private Long buildUserLoginDTO(MimeHeadersDTO headersDTO) {
        return StringUtils.isBlank(headersDTO.getUid()) ? null : Long.valueOf(headersDTO.getUid());
    }

}
