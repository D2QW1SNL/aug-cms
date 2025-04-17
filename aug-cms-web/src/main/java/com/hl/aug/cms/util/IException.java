package com.hl.aug.cms.util;


import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 运营平台的异常类
 * @Author: summer
 * @CreateDate: 2025/4/17 10:41
 * @Version: 1.0.0
 */
@Data
public class IException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4982113344491892263L;

    /**
     * 默认错误码
     */
    private static final String DEFAULT_ERROR_CODE = "-1";

    /**
     * 错误编码
     */
    private String errorCode;

    private String errorMsg;

    public IException(String detailMessage) {
        super(detailMessage);
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorMsg = detailMessage;
    }


    public IException(String errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.errorMsg = detailMessage;
    }
}
