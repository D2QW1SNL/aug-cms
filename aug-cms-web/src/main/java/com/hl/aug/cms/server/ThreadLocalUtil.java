package com.hl.aug.cms.server;



public class ThreadLocalUtil {

    private static ThreadLocal<Long> USER_LOGIN = new ThreadLocal<Long>();

    public static Long getUserLogin() {
        return USER_LOGIN.get();
    }

    public static void setUserLogin(Long userId) {
        USER_LOGIN.set(userId);
    }

    public static void clear() {
        USER_LOGIN.set(null);
    }
}
