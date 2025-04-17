package com.hl.aug.cms.common.exception;



import com.hl.aug.cms.common.enums.CommonErrorEnum;

import java.io.Serializable;

public class CommonErrorException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4982113344491891263L;

    private int code;

    private boolean showErrorMsg = true;

    private boolean showStackTrace = true;

    public CommonErrorException(String errorMsg, int code, Throwable cause) {
        super(errorMsg, cause);
        this.code = code;
    }

    public CommonErrorException(String errorMsg, int code) {
        super(errorMsg);
        this.code = code;
    }

    public CommonErrorException(String errorMsg, int code, boolean showErrorMsg) {
        super(errorMsg);
        this.code = code;
        this.showErrorMsg = showErrorMsg;
    }


    public CommonErrorException(CommonErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getErrorMsg(), cause);
        this.code = errorEnum.getCode();
    }

    public CommonErrorException(CommonErrorEnum errorEnum, String message, Throwable cause) {
        super(message, cause);
        this.code = errorEnum.getCode();
    }

    public CommonErrorException(CommonErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
        this.code = errorEnum.getCode();
    }

    public CommonErrorException(CommonErrorEnum errorEnum, String message) {
        super(message);
        this.code = errorEnum.getCode();
    }

    public CommonErrorException(CommonErrorEnum errorEnum, String message, boolean showErrorMsg) {
        super(message);
        this.code = errorEnum.getCode();
        this.showErrorMsg = showErrorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isShowErrorMsg() {
        return showErrorMsg;
    }

    public void setShowErrorMsg(boolean showErrorMsg) {
        this.showErrorMsg = showErrorMsg;
    }

    public boolean isShowStackTrace() {
        return showStackTrace;
    }

    public void setShowStackTrace(boolean showStackTrace) {
        this.showStackTrace = showStackTrace;
    }
}
