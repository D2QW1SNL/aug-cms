package com.hl.aug.cms.server;

/**
 * @Description: 请求用户的上下文信息
 * @Author: summer
 * @CreateDate: 2022/10/19 11:17
 * @Version: 1.0.0
 */
public class RequestUserContext {

    /**
     * 获取用户ID
     *
     * @return 可能存在null
     */
    public static Long getAppUserId() {
        return ThreadLocalUtil.getUserLogin();
    }
}
