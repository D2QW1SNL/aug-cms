package com.hl.aug.cms.common.util;


import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    public static final Logger COMMON_DEFAULT = LogManager.getLogger("common-default");

    public static final Logger COMMON_ERROR = LogManager.getLogger("common-error");

    public static final Logger INTERCEPTOR = LogManager.getLogger("interceptor");

    public static final Logger HTTP_REMOTE = LogManager.getLogger("http-remote");

    public static final Logger ROCKET_MQ = LogManager.getLogger("rocket-mq");

    public static final Logger DUBBO_LOG = LogManager.getLogger("dubbo-access");

    public static final Logger FFMPEG_LOG = LogManager.getLogger("ffmpeg");

    /**
     * 以逗号分隔对象
     *
     * @param args
     * @return
     */
    private static String buildString(Object... args) {
        StringBuilder sb = new StringBuilder();
        // 对象批量输出
        for (Object arg : args) {
            // 基本变量与null,String类型
            if (null == arg || isPrimitive(arg)) {
                sb.append(arg);
            } else {
                sb.append(JSON.toJSONString(arg));
            }
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void info(Logger logger, Object... args) {
        logger.info(buildString(args));
    }

    public static void warn(Logger logger, Object... args) {
        logger.warn(buildString(args));
    }

    public static void warn(Logger logger, Throwable e, Object... args) {
        logger.warn(buildString(args), e);
    }

    public static void error(Logger logger, Throwable e, Object... args) {
        logger.error(buildString(args), e);
    }

    public static void error(Logger logger, Object... args) {
        logger.error(buildString(args));
    }

    /**
     * 是否为基本数据类型
     *
     * @param target
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static boolean isPrimitive(Object target) {
        Class clazz = target.getClass();
        return String.class.equals(clazz) || clazz.isPrimitive();
    }

}