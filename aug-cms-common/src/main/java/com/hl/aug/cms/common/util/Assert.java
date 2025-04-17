package com.hl.aug.cms.common.util;

import com.hl.aug.cms.common.enums.CommonErrorEnum;
import com.hl.aug.cms.common.exception.CommonErrorException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Assert {

    /**
     * 表达式为false,则抛异常
     *
     * @param expression
     * @param errorEnum
     * @param message
     */
    public static void isTrue(boolean expression, CommonErrorEnum errorEnum, String message, boolean showErrorMsg) {
        if (!expression) {
            throw new CommonErrorException(errorEnum, message, showErrorMsg);
        }
    }


    /**
     * 表达式为false,则抛异常
     *
     * @param expression
     * @param code
     * @param message
     */
    public static void isTrue(boolean expression, int code, String message, boolean showErrorMsg) {
        if (!expression) {
            throw new CommonErrorException(message, code, showErrorMsg);
        }
    }

    /**
     * 对象不为空,则抛异常
     *
     * @param object
     * @param errorEnum
     * @param message
     */
    public static void isNull(Object object, CommonErrorEnum errorEnum, String message, boolean showErrorMsg) {
        if (Objects.nonNull(object)) {
            throw new CommonErrorException(errorEnum, message, showErrorMsg);
        }
    }

    /**
     * 对象为空,则抛异常
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, CommonErrorEnum errorEnum, String message, boolean showErrorMsg) {
        if (Objects.isNull(object)) {
            throw new CommonErrorException(errorEnum, message, showErrorMsg);
        }
    }

    /**
     * 字符串为空,则抛异常
     *
     * @param text
     * @param errorEnum
     * @param message
     */
    public static void hasLength(String text, CommonErrorEnum errorEnum, String message, boolean showErrorMsg) {
        if (StringUtils.isEmpty(text)) {
            throw new CommonErrorException(errorEnum, message, showErrorMsg);
        }
    }
}
