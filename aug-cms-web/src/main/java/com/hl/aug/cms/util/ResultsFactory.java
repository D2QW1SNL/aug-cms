package com.hl.aug.cms.util;


import com.hl.aug.cms.response.PageData;
import com.hl.aug.cms.response.PaginatedResultData;
import com.hl.aug.cms.response.PaginatedResultList;
import com.hl.aug.cms.response.ResultsData;
import com.hl.aug.cms.response.WinSportBaseResp;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class ResultsFactory {

    /**
     * error
     *
     * @param code
     * @param errorMsg
     * @return
     * @Author ruanqunfeng
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<T> buildError(int code, String errorMsg) {
        return WinSportBaseResp.error(code, errorMsg);
    }

    /**
     * 带参数回传的统一错误处理
     *
     * @param errorMsg 错误信息
     * @param data     回传的参数信息
     * @param <T>      回传参数类型
     * @return WinSportBaseResp
     */
    public static <T> WinSportBaseResp<T> buildError(String errorMsg, T data) {
        WinSportBaseResp<T> resp = WinSportBaseResp.error(ErrorCodeConstant.CODE_ERROR, errorMsg);
        resp.setData(data);
        return resp;
    }

    /**
     * error
     *
     * @param errorMsg
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<T> buildAssertError(String errorMsg) {
        return WinSportBaseResp.error(ErrorCodeConstant.PARAM_ASSERT_ERROR, errorMsg);
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static WinSportBaseResp<Object> buildResult() {
        WinSportBaseResp<Object> result = WinSportBaseResp.ok();
        result.setData(Collections.EMPTY_MAP);
        return result;
    }


    public static <T> WinSportBaseResp<PageData<T>> buildPageResult(List<T> list, Integer size) {
        WinSportBaseResp<PageData<T>> resp = WinSportBaseResp.ok();
        PageData<T> pageData = new PageData<>();
        if (CollectionUtils.isEmpty(list)) {
            pageData.setList(Collections.emptyList());
            // 设置最后一页
            pageData.setEndPage(true);
        } else {
            pageData.setList(list);
            if (list.size() < size) {
                pageData.setEndPage(true);
            }
        }
        resp.setData(pageData);
        return resp;
    }

    public static <T> WinSportBaseResp<PageData<T>> buildPageResult(List<T> list, boolean endPage) {
        WinSportBaseResp<PageData<T>> resp = WinSportBaseResp.ok();
        PageData<T> pageData = new PageData<>();
        if (CollectionUtils.isEmpty(list)) {
            pageData.setList(Collections.emptyList());
            pageData.setEndPage(true);
        } else {
            pageData.setList(list);
            pageData.setEndPage(endPage);
        }
        resp.setData(pageData);
        return resp;
    }

    public static <T> WinSportBaseResp<PageData<T>> buildPageResult(List<T> list) {
        return buildPageResult(list, false);
    }

    public static <T> WinSportBaseResp<PageData<T>> buildPageResult(PageData<T> pageData) {
        WinSportBaseResp<PageData<T>> commonResult = WinSportBaseResp.ok();
        commonResult.setData(pageData);
        return commonResult;
    }

    public static <T> WinSportBaseResp<PaginatedResultData<T>> buildPaginatedResult(List<T> list, Integer total, Integer size, Integer currentPage) {
        WinSportBaseResp<PaginatedResultData<T>> commonResult = WinSportBaseResp.ok();
        PaginatedResultData<T> paginatedResultData = new PaginatedResultData<>();
        if (CollectionUtils.isEmpty(list)) {
            paginatedResultData.setData(Collections.emptyList());
        } else {
            paginatedResultData.setData(list);
        }
        int totalPage = 0;
        if (size != 0) {
            totalPage = total / size;
            if (total != 0 && total % size != 0) {
                totalPage++;
            }
        }
        paginatedResultData.setTotalCount(total);
        paginatedResultData.setCurrentPage(currentPage);
        paginatedResultData.setTotalPage(totalPage);
        commonResult.setData(paginatedResultData);
        return commonResult;
    }


    public static <T> WinSportBaseResp<PaginatedResultList<T>> buildPaginatedResultList(List<T> list, Integer total, Integer size, Integer currentPage) {
        WinSportBaseResp<PaginatedResultList<T>> commonResult = WinSportBaseResp.ok();
        PaginatedResultList<T> paginatedResultData = new PaginatedResultList<>();
        if (CollectionUtils.isEmpty(list)) {
            paginatedResultData.setList(Collections.emptyList());
        } else {
            paginatedResultData.setList(list);
        }
        int totalPage = 0;
        if (size != 0) {
            totalPage = total / size;
            if (total != 0 && total % size != 0) {
                totalPage++;
            }
        }
        paginatedResultData.setTotalCount(total);
        paginatedResultData.setCurrentPage(currentPage);
        paginatedResultData.setTotalPage(totalPage);
        commonResult.setData(paginatedResultData);
        return commonResult;
    }

    public static <T> WinSportBaseResp<PaginatedResultData<T>> buildPaginatedResultV2(List<T> list, Integer total) {
        WinSportBaseResp<PaginatedResultData<T>> commonResult = WinSportBaseResp.ok();
        PaginatedResultData<T> paginatedResultData = new PaginatedResultData<>();
        if (CollectionUtils.isEmpty(list)) {
            paginatedResultData.setData(Collections.emptyList());
        } else {
            paginatedResultData.setData(list);
        }
        paginatedResultData.setTotalCount(total);
        commonResult.setData(paginatedResultData);
        return commonResult;
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<T> buildError() {
        return WinSportBaseResp.error(ErrorCodeConstant.SYS_ERROR, "系统繁忙,请稍后再试");
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static WinSportBaseResp<Object> buildObjectError() {
        return WinSportBaseResp.error(ErrorCodeConstant.SYS_ERROR, "系统繁忙,请稍后再试");
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static WinSportBaseResp<Object> buildObjectError(String errorMsg) {
        return WinSportBaseResp.error(ErrorCodeConstant.SYS_ERROR, errorMsg);
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<T> buildError(String errorMsg) {
        return WinSportBaseResp.error(ErrorCodeConstant.CODE_ERROR, errorMsg);
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<T> buildResult(T t) {
        return new WinSportBaseResp<T>(200, "", t);
    }

    /**
     * 系统繁忙
     *
     * @return
     * @Author
     * @CreateDate 2017年7月26日
     */
    public static <T> WinSportBaseResp<ResultsData<T>> buildListResult(List<T> list) {

        WinSportBaseResp<ResultsData<T>> commonResult = WinSportBaseResp.ok();
        ResultsData<T> resultsData = new ResultsData<>();

        if (CollectionUtils.isEmpty(list)) {
            resultsData.setList(Collections.emptyList());
        } else {
            resultsData.setList(list);
        }
        commonResult.setData(resultsData);
        return commonResult;
    }

    public static final WinSportBaseResp<Object> SYS_ERROR = ResultsFactory.buildError(ErrorCodeConstant.SYS_ERROR, "系统繁忙,请稍后再试");

    /**
     * 基本参数丢失
     */
    public static final WinSportBaseResp<Object> COMMON_DATA_MISS = ResultsFactory.buildError(ErrorCodeConstant.COMMON_DATA_MISS, "基本参数丢失");

    /**
     * 基本参数不全
     */
    public static final WinSportBaseResp<Object> COMMON_DATA_UNFULL = ResultsFactory.buildError(ErrorCodeConstant.COMMON_DATA_MISS,
            "did,token,sign,ver,noncestr 等数据丢失");

    /**
     * 平台类型错误
     */
    public static final WinSportBaseResp<Object> PLATFORM_ERROR = ResultsFactory.buildError(ErrorCodeConstant.PLATFORM_ERROR, "平台类型错误");

    /**
     * 登录令牌类型错误
     */
    public static final WinSportBaseResp<Object> TOKEN_ERROR = ResultsFactory.buildError(ErrorCodeConstant.TOKEN_ERROR, "登录已过期，请重新登录");

    /**
     * 用户冻结错误
     */
    public static final WinSportBaseResp<Object> USER_FROZEN_ERROR = ResultsFactory.buildError(ErrorCodeConstant.USER_FROZEN, "用户冻结");

    /**
     * 用户不存在
     */
    public static final WinSportBaseResp<Object> USER_NOT_EXIST_ERROR = ResultsFactory.buildError(ErrorCodeConstant.USER_NOT_EXIST, "用户不存在");

    /**
     * 签名错误
     */
    public static final WinSportBaseResp<Object> SIGN_ERROR = ResultsFactory.buildError(ErrorCodeConstant.SIGN_ERROR, "签名错误");

    /**
     * 接口版本为空
     */
    public static final WinSportBaseResp<Object> API_VER_ERROR = ResultsFactory.buildError(ErrorCodeConstant.APP_VER_ERROR, "接口版本为空");

    /**
     * ip不允许版本为空
     */
    public static final WinSportBaseResp<Object> IP_ERROR = ResultsFactory.buildError(ErrorCodeConstant.COMMON_DATA_MISS, "ip不允许访问");

    /**
     * app版本不符合要求
     */
    public static final WinSportBaseResp<Object> APP_VER_ERROR = ResultsFactory.buildError(ErrorCodeConstant.APP_VER_ERROR, "app版本太低，请到app应用市场升级到最新版本");

    /**
     * rsa解密失败
     */
    public static final WinSportBaseResp<Object> RSA_DES_ERROR = ResultsFactory.buildError(ErrorCodeConstant.RSA_DES, "解密失败");

    public static final WinSportBaseResp<Object> LOGOUT_USER = ResultsFactory.buildError(ErrorCodeConstant.LOGOUT_USER, "抱歉，当前账号已注销");

    public interface ErrorCodeConstant {

        int CODE_SUCCESS = 200;

        int CODE_ERROR = 201;

        /**
         * 基本参数丢失
         */
        int COMMON_DATA_MISS = 40001;

        /**
         * 平台类型错误
         */
        int PLATFORM_ERROR = 40002;

        /**
         * 登录令牌类型错误 唯一唯一唯一唯一唯一
         */
        int TOKEN_ERROR = 40003;

        /**
         * 用户冻结 唯一唯一唯一唯一唯一
         */
        int USER_FROZEN = 40005;

        /**
         * 用户不存在 唯一唯一唯一唯一唯一
         */
        int USER_NOT_EXIST = 40006;

        /**
         * 签名错误
         */
        int SIGN_ERROR = 40007;

        /**
         * rsa解密失败
         */
        int RSA_DES = 40008;

        /**
         * 参数获取失败
         */
        int PARAM_ASSERT_ERROR = 40009;

        /**
         * 版本错误
         */
        int APP_VER_ERROR = 40010;

        /**
         * 登录过期
         */
        int LOGIN_OVERDUE = 40011;

        /**
         * 商品不存在
         */
        int GOODS_NON_EXISTENT = 40012;

        /**
         * 商品下架
         */
        int GOODS_OFFLINE = 40013;

        /**
         * 用户未登录
         */
        int NOT_LOGIN = 40014;

        /**
         * 用户未授权
         */
        int UNAUTHORIZED = 40015;

        /**
         * 违规用户
         */
        int VIOLATION_USER = 40025;

        /**
         * 注销用户
         */
        int LOGOUT_USER = 40026;

        /**
         * 重复支付VIP
         */
        int REPEAT_PAYMENT_VIP = 40027;

        /**
         * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
         * 调用分钟频率受限(目前5000次/分钟，会调整)，如需大量小程序码，建议预生成。
         */
        int WX_QRCODE_LIMIT = 45009;

        /**
         * 创蓝闪验验证失败
         */
        int FLASH_CHECK_FAIL = 45010;

        /**
         * 系统错误
         */
        int SYS_ERROR = 50001;
    }
}