package com.hl.aug.cms.common.util;


import com.hl.aug.cms.common.enums.DateTimeFormatterEnum;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalDateUtil {

    /**
     * yyyyMMddHHmmss
     */
    static DateTimeFormatter YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

    static DateTimeFormatter YYYYMM = DateTimeFormatter.ofPattern("yyyyMM");

    static DateTimeFormatter YYYY = DateTimeFormatter.ofPattern("yyyy");

    static DateTimeFormatter YYYYMMDDHHMMSS2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static DateTimeFormatter YYYY_MM_DD_HMS = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static String format(LocalDate localDate) {
        return format(localDate, 3);
    }

    public static String formatYYYY(LocalDate localDate) {
        try {
            if (Objects.nonNull(localDate)) {
                return YYYY.format(localDate);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYYMM(LocalDate localDate) {
        try {
            if (Objects.nonNull(localDate)) {
                return YYYYMM.format(localDate);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYYMMDD(LocalDateTime localDateTime) {
        try {
            return YYYYMMDD.format(localDateTime);
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYYMM(LocalDateTime localDate) {
        try {
            if (Objects.nonNull(localDate)) {
                return YYYYMM.format(localDate);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYYMMDD(LocalDate localDate) {
        try {
            if (Objects.nonNull(localDate)) {
                return YYYYMMDD.format(localDate);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYY_MM_DD_HMS(LocalDateTime localDateTime) {
        try {
            if (Objects.nonNull(localDateTime)) {
                return YYYY_MM_DD_HMS.format(localDateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String formatYYYY_MM_DD(LocalDateTime localDateTime) {
        try {
            if (Objects.nonNull(localDateTime)) {
                return YYYY_MM_DD.format(localDateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将LocalDate转化为字符串格式
     *
     * @param localDate
     * @param type
     * @return
     */
    public static String format(LocalDate localDate, Integer type) {
        try {
            if (Objects.nonNull(localDate)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                return format.getFormatter().format(localDate);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static LocalDate toLocalDate(String date) {
        return toLocalDate(date, 5);
    }

    /**
     * 将String转化为LocalDateTime
     *
     * @param date
     * @param type
     * @return
     */
    public static LocalDate toLocalDate(String date, Integer type) {
        try {
            if (StringUtils.isNotBlank(date)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                return LocalDate.parse(date, format.getFormatter());
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @param localDateTime
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static String getDateStr(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return YYYYMMDDHHMMSS2.format(localDateTime);

    }

    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @param localDateTime
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static Long getDateLong(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return Long.valueOf(YYYYMMDDHHMMSS.format(localDateTime));

    }

    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static Long getDateLong() {
        return Long.valueOf(YYYYMMDDHHMMSS.format(LocalDateTime.now()));
    }

    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static LocalDateTime fromTimestampOfSecond(Long time) {
        LocalDateTime createTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.of("+8"));
        return createTime;
    }


    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @param localDateTime
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static Long getTimestamp(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return localDateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond();

    }

    /**
     * 返回YYYYMMDDHHMMSS
     *
     * @return
     * @Author buganhuang
     * @CreateDate 2018年4月27日
     */
    public static Long getTimestamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();

    }

    /**
     * 获取上月月底
     *
     * @return
     */
    public static LocalDate getLastMonthDay(LocalDate localDate) {
        LocalDate date = Objects.nonNull(localDate) ? localDate : LocalDate.now();
        return LocalDate.of(date.getYear(), date.getMonth(), 1).minusDays(1);
    }

    public static LocalDate getLastMonthDay() {
        return getLastMonthDay(null);
    }

    /**
     * 是否是相同的月份
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameMonth(LocalDate d1, LocalDate d2) {
        return Objects.equals(LocalDateUtil.formatYYYYMM(d1), LocalDateUtil.formatYYYYMM(d2));
    }

    public static Boolean betweenAnd(LocalDate startTime, LocalDate endTime) {
        LocalDate now = LocalDate.now();
        return betweenAnd(now, startTime, endTime);
    }

    public static Boolean betweenAnd(LocalDate time, LocalDate startTime, LocalDate endTime) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }
}