package com.hl.aug.cms.common.util;

import java.util.UUID;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/5/30 13:50
 * @Version: 1.0.0
 */
public class UUIDUtil {


    public static String get() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

