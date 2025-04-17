package com.hl.aug.cms.common.util;

import java.time.Duration;
import java.time.LocalTime;

public class LocalTimeUtil {

    /**
     * 判断现在时间点是否在预定时间点内
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static Boolean betweenAnd( LocalTime startTime, LocalTime endTime) {
        LocalTime now = LocalTime.now();
        return betweenAnd(now, startTime, endTime);
    }

    /**
     *  判断时间点是否在预定时间点内
     * @param time 指定的时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static Boolean betweenAnd(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    /**
     * 判断当前时间是否在指定时间之后
     * @param startTime 开始时间
     * @return
     */
    public static Boolean after(LocalTime startTime) {
        LocalTime now = LocalTime.now();
        return after(now, startTime);
    }

    /**
     * 判断时间在指定时间之后
     * @param endTime 指定时间
     * @param startTime 开始时间
     * @return
     */
    public static Boolean after(LocalTime endTime, LocalTime startTime) {
        return endTime.isAfter(startTime);
    }


    /**
     * 判断当前时间在指定时间之前
     * @param endTime 结束时间
     * @return
     */
    public static Boolean before(LocalTime endTime) {
        LocalTime now = LocalTime.now();
        return before(now, endTime);
    }

    /**
     * 判断时间在指定时间之前
     * @param time 指定时间
     * @param endTime 结束时间
     * @return
     */
    public static Boolean before(LocalTime time, LocalTime endTime) {
        return time.isBefore(endTime);
    }


    /**
     * 判断
     * @param time
     * @return
     */
    public static Long getTimeline(LocalTime time) {
        LocalTime now = LocalTime.now();
        Duration duration = Duration.between(now, time);
        return duration.toMillis() / 1000;
    }
}
