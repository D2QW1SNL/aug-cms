package com.hl.aug.cms.response;

import com.hl.aug.cms.common.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WinSportBaseResp<T> implements Serializable {

    private Integer code;

    private String msg;

    private boolean success;

    private T data;

    public WinSportBaseResp() {
    }

    public WinSportBaseResp(Integer code, String msg, T data) {
        this.code = code;
        if (code == 200) {
            this.success = true;
        }
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速创建成功的返回值
     */
    public static <T> WinSportBaseResp<T> error(Integer code, String msg) {
        return new WinSportBaseResp<T>(code, msg, null);
    }

    public static <T> WinSportBaseResp<T> error(ResultCodeEnum resultCodeEnum) {
        return new WinSportBaseResp<T>(resultCodeEnum.getCode(), resultCodeEnum.msg, false, null);
    }

    public static <T> WinSportBaseResp<T> ok() {
        return new WinSportBaseResp<T>(200, "请求成功", null);
    }

    public static <T> WinSportBaseResp<T> ok(T data) {
        return new WinSportBaseResp<T>(200, "请求成功", data);
    }
}
