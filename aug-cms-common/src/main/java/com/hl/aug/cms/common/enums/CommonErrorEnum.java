package com.hl.aug.cms.common.enums;

import java.util.Objects;

public enum CommonErrorEnum {

    // 成功
    SUCCESS(200, "success", "成功", LogLevelEnum.DEBUG),

    // ================以下是业务常用错误码================
    // 固定看到【系统繁忙,请稍后再试】错误
    PARAM_CHECK_ERROR(20001, "系统繁忙,请稍后再试", "参数校验错误或丢失", LogLevelEnum.ERROR),

    BIZ_RULE_CHECK_ERROR(20002, "系统繁忙,请稍后再试", "业务规则校验失败", LogLevelEnum.INFO),


    // ================以下是系统常用错误码================
    SYSTEM_ERROR(30001, "系统繁忙,请稍后再试", "系统异常", LogLevelEnum.ERROR),

    // 非法请求(系统约定的请求header、验签),不打印堆栈信息
    ILLEGAL_REQUEST(30002, "系统繁忙,请稍后再试", "非法请求", LogLevelEnum.WARN),

    FLOW_LIMIT_ERROR(30003, "系统太热了，请稍后再试~~~", "系统限流", LogLevelEnum.INFO),


    // ================以下是特殊错误码,APP或者web需要特殊场景处理================
    LOGIN_ERROR(40003, "登录已过期，请重新登录", "登录错误", LogLevelEnum.INFO),

    LOGIN_NEED_TOKEN_ERROR(44003, "请登录一下", "强制登录接口没有token错误", LogLevelEnum.INFO),

    APP_VERSION_LIMIT_ERROR(40010, "app版本太低，请到app应用市场升级到最新版本", "版本过低错误", LogLevelEnum.INFO),

    GOODS_NON_EXISTENT(40012, "商品已下架", "商品不存在", LogLevelEnum.INFO),

    GOODS_OFFLINE(40013, "商品已下架", "商品不存在", LogLevelEnum.INFO),

    VIOLATION_USER_ERROR(40025, "抱歉，您存在违规，请重新再试", "违规用户", LogLevelEnum.INFO),

    FLASH_CHECK_ERROR(40028, "一键登录异常，请重新再试", "闪验异常", LogLevelEnum.INFO),

    SUGGEST_APP_VERSION_UPDATE(40030, "app版本太低，请到app应用市场升级到最新版本", "建议升级app", LogLevelEnum.INFO),

    SCENENAMETOOLONG(45013, "场景类型字段过长", "场景类型字段长度过长", LogLevelEnum.INFO),

    SCENENAMENOTREGEX(45014, "场景类型字段不符合要求", "场景类型字段不符合要求", LogLevelEnum.INFO),

    ;

    private int code;

    private String errorMsg;

    private String name;

    private LogLevelEnum logLevel;

    CommonErrorEnum(int code, String errorMsg, String name, LogLevelEnum logLevel) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.name = name;
        this.logLevel = logLevel;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getName() {
        return name;
    }

    public LogLevelEnum getLogLevel() {
        return logLevel;
    }

    public static CommonErrorEnum getByCode(int code) {
        for (CommonErrorEnum e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return SYSTEM_ERROR;
    }

    public static CommonErrorEnum getByCodeV2(int code) {
        for (CommonErrorEnum e : values()) {
            if (Objects.equals(e.code, code)) {
                return e;
            }
        }
        return null;
    }
}
