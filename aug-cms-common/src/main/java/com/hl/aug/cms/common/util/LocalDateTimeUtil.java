package com.hl.aug.cms.common.util;

import com.hl.aug.cms.common.enums.DateTimeFormatterEnum;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

public class LocalDateTimeUtil {

    public static final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);
    private static final ZoneId zone = ZoneId.systemDefault();
    private static final long ONE_DAY_SECONDS = 86400;

    public static String format(LocalDateTime dateTime) {
        return format(dateTime, 5);
    }

    /**
     * 将LocalDateTime转化为字符串格式
     *
     * @param dateTime
     * @param type
     * @return
     */
    public static String format(LocalDateTime dateTime, Integer type) {
        try {
            if (Objects.nonNull(dateTime)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                return format.getFormatter().format(dateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为字符串格式
     *
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatterEnum format) {
        try {
            if (Objects.nonNull(dateTime)) {
                return format.getFormatter().format(dateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(String date) {
        return toLocalDateTime(date, 5);
    }

    /**
     * 将String转化为LocalDateTime
     *
     * @param date
     * @param type
     * @return
     */
    public static LocalDateTime toLocalDateTime(String date, Integer type) {
        try {
            if (StringUtils.isNotBlank(date)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                //只有年月的话,需要使用LocalDate去解析
                switch (format) {
                    case FORMAT_YM:
                        date += "01";
                    case FORMAT_YMD:
                        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatterEnum.FORMAT_YMD.getFormatter()), LocalTime.MIN);
                    case FORMAT_Y_M:
                        date += "-01";
                    case FORMAT_Y_M_D:
                        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatterEnum.FORMAT_Y_M_D.getFormatter()), LocalTime.MIN);
                }
                return LocalDateTime.parse(date, format.getFormatter());
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将Date转化为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (Objects.nonNull(date)) {
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            return localDateTime;
        }
        return null;
    }

    /**
     * 将Long转化为LocalDateTime[只支持10位和13位Long]
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        if (Objects.nonNull(timestamp)) {
            Integer length = String.valueOf(timestamp).length();
            Instant instant = null;
            if (length == 10) {
                instant = Instant.ofEpochSecond(timestamp);
            } else if (length == 13) {
                instant = Instant.ofEpochMilli(timestamp);
            }
            return Objects.nonNull(instant) ? LocalDateTime.ofInstant(instant, zone) : null;
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为Date
     *
     * @param dateTime
     * @return
     */
    public static Date toDate(LocalDateTime dateTime) {
        if (Objects.nonNull(dateTime)) {
            Instant instant = dateTime.atZone(zone).toInstant();
            return Date.from(instant);
        }
        return null;
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(zone).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(zone).toInstant().getEpochSecond();
    }

    /**
     * 获取该月月初时间
     *
     * @return
     */
    public static LocalDateTime firstDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        return LocalDateTime.of(localDate.with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDateTime atStartOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atStartOfDay();
        }
        return null;
    }

    /**
     * 获取输入该天的的起始
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime atStartOfDay(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.toLocalDate().atStartOfDay();
        }
        return null;
    }

    public static LocalDateTime atEndOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.plusDays(1).atStartOfDay().minusSeconds(1);
        }
        return null;
    }

    public static LocalDateTime atEndOfDay(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.toLocalDate().plusDays(1).atStartOfDay().minusSeconds(1);
        }
        return null;
    }

    public static Boolean betweenAnd(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        return betweenAnd(now, startTime, endTime);
    }

    public static Boolean betweenAnd(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    /**
     * 计算【充值】后结束时间
     * 如果开始时间为00:00:00, 则包含当天
     * 举例：delta=2
     * startTime=5.22 00:00:00,  则endTime=5.23 23:59:58
     * startTime=5.22 00:00:01,  则endTime=5.24 23:59:58
     *
     * @param startTime
     * @param delta
     * @return
     */
    public static LocalDateTime getEndTimeWithCharge(LocalDateTime startTime, Integer delta) {
        if (Objects.isNull(startTime) || Objects.isNull(delta)) {
            return null;
        }
        if (equals(LocalTime.MIN, startTime.toLocalTime())) {
            delta--;
        }
        return LocalDateTime.of(startTime.toLocalDate().plusDays(delta), MAX_TIME);
    }

    /**
     * 计算【升级】后补差天数
     * 如果当前时间为23:59:59, 则不包含当天
     * 举例：endTime=5.24 23:59:59
     * now=5.22 23:59:58,  则delta=3
     * now=5.22 23:59:59,  则delta=2
     *
     * @param endTime
     * @return
     */
    public static Integer getDelta(LocalDateTime endTime) {
        if (Objects.isNull(endTime)) {
            return null;
        }
        return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), endTime);
    }

    /**
     * LocalTime只比较时分秒
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean equals(LocalTime o1, LocalTime o2) {
        if (o1 == o2) {
            return true;
        }

        if (null == o1 || null == o2) {
            return false;
        }

        return o1.getHour() == o2.getHour() && o1.getMinute() == o2.getMinute() && o1.getSecond() == o2.getSecond();
    }

    /**
     * 从现在 到 结束时间 之间的时间间隔（秒）
     *
     * @param activityEndTime
     * @param delayedHours    需要延长的时间（小时）
     * @return
     */
    public static Long getTimeline(LocalDateTime activityEndTime, int delayedHours) {
        Duration duration = Duration.between(LocalDateTime.now(), activityEndTime);
        return duration.toMillis() / 1000 + delayedHours * 60 * 60;
    }

    public static String remainTime(LocalDateTime endTime) {
        Long delta = ChronoUnit.SECONDS.between(LocalDateTime.now(), endTime);
        if (delta.compareTo(0L) <= 0) {
            return "已失效";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Long days = delta / ONE_DAY_SECONDS;
        if (days.compareTo(0L) == 0) {
            Long hours = delta / 3600;
            if (hours.compareTo(0L) == 0) {
                Long seconds = delta / 60;
                if (seconds.compareTo(0L) == 0) {
                    return "剩余不足1分钟";
                }
                return stringBuilder.append("剩余").append(seconds).append("分钟").toString();
            }
            return stringBuilder.append("剩余").append(hours).append("小时").toString();
        }
        return stringBuilder.append("剩余").append(days + 1).append("天").toString();
    }
}
