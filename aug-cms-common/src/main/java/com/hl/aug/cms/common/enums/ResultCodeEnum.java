package com.hl.aug.cms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCodeEnum {

    Success(200, "请求成功！", true),
    SYS_ERROR(500, "系统错误", false),
    DATA_DOES_NOT_EXIST(10000, "数据不存在！", false),
    UPDATE_ERROR(10001, "更新错误！", false),
    ERROR_PROMPT(50000, "错误提示", false),
    ;


    /**
     * 异常编码
     */
    public int code;

    /**
     * 异常信息
     */
    public String msg;

    boolean success;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }

    ResultCodeEnum(int code, String msg, Boolean b) {
        this.code = code;
        this.msg = msg;
        this.success = b;
    }
}
