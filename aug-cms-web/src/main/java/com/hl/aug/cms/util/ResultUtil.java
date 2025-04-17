package com.hl.aug.cms.util;


import com.hl.aug.cms.common.enums.CommonErrorEnum;
import com.hl.aug.cms.common.exception.CommonErrorException;
import com.hl.aug.cms.response.ListData;
import com.hl.aug.cms.response.PageData;
import com.hl.aug.cms.response.WinSportBaseResp;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultUtil {

    private static final Logger logger = LogManager.getLogger("common-default");

    private static final Logger loggerError = LogManager.getLogger("common-error");

    private static final Map EMPTY = new HashMap();

    /**
     * 【对象】请求成功
     * 【只能rpc协议使用】
     *
     * @return
     */
    public static <T> WinSportBaseResp<T> successForRpc() {
        WinSportBaseResp<T> result = new WinSportBaseResp<>();
        result.setCode(CommonErrorEnum.SUCCESS.getCode());
        result.setMsg(CommonErrorEnum.SUCCESS.getErrorMsg());
        return result;
    }

    /**
     * 【对象】请求成功
     * 【只能http协议使用】
     *
     * @return
     */
    public static <T> WinSportBaseResp<T> successForHttp() {
        WinSportBaseResp<T> result = new WinSportBaseResp<>();
        result.setCode(CommonErrorEnum.SUCCESS.getCode());
        result.setMsg(CommonErrorEnum.SUCCESS.getErrorMsg());
        result.setData((T) EMPTY);
        return result;
    }

    public static <T> WinSportBaseResp<T> success(T object) {
        WinSportBaseResp<T> result = new WinSportBaseResp<>();
        result.setCode(CommonErrorEnum.SUCCESS.getCode());
        result.setMsg(CommonErrorEnum.SUCCESS.getErrorMsg());
        result.setData(object);
        return result;
    }


    /**
     * 【列表】请求成功
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> WinSportBaseResp<ListData<T>> listSuccess(List<T> list) {
        WinSportBaseResp<ListData<T>> result = new WinSportBaseResp<>();
        ListData<T> listData = new ListData<>();
        listData.setList(CollectionUtils.isEmpty(list) ? Collections.emptyList() : list);
        result.setCode(CommonErrorEnum.SUCCESS.getCode());
        result.setMsg(CommonErrorEnum.SUCCESS.getErrorMsg());
        result.setData(listData);
        return result;
    }

    /**
     * 【分页】请求成功
     *
     * @param list
     * @param size
     * @param <T>
     * @return
     */
    public static <T> WinSportBaseResp<PageData<T>> pageSuccess(List<T> list, Integer size) {
        boolean endPage = CollectionUtils.isEmpty(list) || list.size() < size;
        return pageSuccess(list, endPage);
    }

    public static <T> WinSportBaseResp<PageData<T>> pageSuccess(List<T> list, boolean endPage) {
        PageData<T> pageData = new PageData<>();
        if (CollectionUtils.isEmpty(list)) {
            pageData.setList(Collections.emptyList());
            pageData.setEndPage(true);
        } else {
            pageData.setList(list);
            pageData.setEndPage(endPage);
        }
        return pageSuccess(pageData);
    }

    public static <T> WinSportBaseResp<PageData<T>> pageSuccess(PageData<T> pageData) {
        WinSportBaseResp<PageData<T>> result = new WinSportBaseResp<>();
        result.setCode(CommonErrorEnum.SUCCESS.getCode());
        result.setMsg(CommonErrorEnum.SUCCESS.getErrorMsg());
        result.setData(pageData);
        return result;
    }

    /**
     * 请求失败
     *
     * @param <T>
     * @return
     */
    public static <T> WinSportBaseResp<T> failed() {
        return failed(CommonErrorEnum.SYSTEM_ERROR);
    }

    public static <T> WinSportBaseResp<T> failed(CommonErrorEnum errorCode) {
        return failed(errorCode.getCode(), errorCode.getErrorMsg());
    }

    public static <T> WinSportBaseResp<T> failed(CommonErrorEnum errorCode, String message) {
        return failed(errorCode.getCode(), message);
    }

    public static <T> WinSportBaseResp<T> failed(int code, String message) {
        WinSportBaseResp<T> result = new WinSportBaseResp<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    /**
     * 验证结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> T checkData(WinSportBaseResp<T> result) {
        if (Objects.isNull(result)) {
            throw new CommonErrorException(CommonErrorEnum.SYSTEM_ERROR);
        }
        if (!Objects.equals(result.getCode(), CommonErrorEnum.SUCCESS.getCode())) {
            throw new CommonErrorException(CommonErrorEnum.getByCode(result.getCode()), result.getMsg());
        }
        return result.getData();
    }

    /**
     * 验证结果,响应msg透传
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> T checkDataUser(WinSportBaseResp<T> result) {
        if (Objects.isNull(result)) {
            throw new CommonErrorException(CommonErrorEnum.SYSTEM_ERROR);
        }
        if (!Objects.equals(result.getCode(), CommonErrorEnum.SUCCESS.getCode())) {
            throw new CommonErrorException(CommonErrorEnum.BIZ_RULE_CHECK_ERROR, result.getMsg());
        }
        return result.getData();
    }

    /**
     * 验证结果并捕获异常,最差返回null
     *
     * @param result
     * @param scene
     * @param <T>
     * @return
     */
    public static <T> T catchData(WinSportBaseResp<T> result, String scene) {
        return catchData(result, scene, true);
    }

    public static <T> T catchData(WinSportBaseResp<T> result, String scene, boolean error) {
        try {
            return ResultUtil.checkData(result);
        } catch (Throwable t) {
            if (error) {
                loggerError.error(scene, t);
            } else {
                logger.warn(scene, t.getMessage());
            }
        }
        return null;
    }
}
