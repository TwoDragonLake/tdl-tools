/*
 * Copyright (C) 2018 The TwoDragonLake Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twodragonlake.tools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基本实体类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/12/26 14:15
 */
public class DateUtils {

    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static Date parseDate(java.sql.Date date) {
        return date;
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(date);
    }

    public static Date parseDate(String date, String format)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static java.sql.Date parseSqlDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                java.text.DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getDaysByWeek(Date date)
            throws Exception {
        date = DateUtils.getDate(DateUtils.format1(date));
        List<String> days = new ArrayList<>();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        String firstWeek = DateUtils.getFirstWeekDay(date);
        for (int i = 0; i < 7; i++) {
            Date firstWeekDate = DateUtils.getdate1(firstWeek);
            firstWeekDate = DateUtils.addDate(firstWeekDate, i);
            int j = DateUtils.diffDate(date, firstWeekDate);
            if (j >= 0) {
                days.add(DateUtils.format1(firstWeekDate));
            } else {
                break;
            }
        }
        return days;
    }

    public static Date getNextWeek(Date date, int count) {
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(date);
        strDate.add(Calendar.DATE, count * 7);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(strDate.get(Calendar.YEAR),
                strDate.get(Calendar.MONTH), strDate.get(Calendar.DATE));
        return currentDate.getTime();
    }

    /**
     * 根据日期获取天
     *
     * @param date date
     * @return List
     */
    public static List<String> getDaysByDate(Date date) {
        List<String> days = new ArrayList<String>();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        int dayss = DateUtils.getDay(date);
        String monthStr = DateUtils.format(date, "yyyy-MM");
        for (int i = 1; i <= dayss; i++) {
            String day;
            if (i < 10) {
                day = monthStr + "-0" + i;
            } else {
                day = monthStr + "-" + i;
            }
            days.add(day);
        }
        return days;
    }

    /**
     * 当前时间的周一
     *
     * @param theDate theDate
     * @return String
     */
    @SuppressWarnings("unused")
    public static String getFirstWeekDay(Date theDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(theDate);
        // 取得本周一
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
    }

    /**
     * 当前时间的周日
     *
     * @param theDate theDate
     * @return String
     */
    @SuppressWarnings("unused")
    public static String getLastWeekDay(Date theDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(theDate);
        // 取得本周日
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtils.format(new Date(calendar.getTime().getTime()
                + (6 * 24 * 60 * 60 * 1000)), "yyyy-MM-dd") + " 23:59:59";
    }

    /**
     * 当月第一天
     *
     * @param theDate theDate
     * @return String
     */
    @SuppressWarnings("unused")
    public static String getFirstDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first + " 00:00:00";
    }

    /**
     * 获取一月 month为1就是上一个月 为-1就是下一个月
     *
     * @param theDate theDate
     * @param month   month
     * @return Date
     * @throws ParseException ParseException
     */
    @SuppressWarnings("unused")
    public static Date getUpMonth(Date theDate, int month)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        c.add(Calendar.MONTH, month);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(c.getTime());
        return format.parse(time);
    }

    /**
     * 当月最后一天
     *
     * @param theDate theDate
     * @return String
     */
    @SuppressWarnings("unused")
    public static String getLastDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH,
                gcLast.getActualMaximum(Calendar.DAY_OF_MONTH));
        String s = df.format(gcLast.getTime());
        return s + " 23:59:59";
    }

    public static String getMinDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(theDate);
        return s + " 00:00:00";
    }

    public static String getMaxDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(theDate);
        return s + " 23:59:59";
    }

    public static String format(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    public static String format1(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static String getDate(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    public static String getDate(Date date, String formatStr) {
        return format(date, formatStr);
    }

    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期相加
     *
     * @param date Date
     * @param day  int
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减(返回秒值)
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static Long diffDateTime(Date date, Date date1) {
        return (getMillis(date) - getMillis(date1)) / 1000;
    }

    public static Date getDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static Date getDate(String date, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static Date getJsonDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.parse(date);
    }

    public static Date getdate1(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public static Date getMaxTimeByStringDate(String date)
            throws Exception {
        String maxTime = date + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(maxTime);
    }

    /**
     * 获得当前时间
     *
     * @return Date
     */
    public static Date getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = DateUtils.getDateTime(date);
        try {
            return sdf.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getCurrentDateTimeToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(getCurrentDateTime());
    }

    public static String getCurrentDateTimeToStr2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(getCurrentDateTime());
    }

    public static Long getWmsupdateDateTime() {
        Calendar cl = Calendar.getInstance();

        return cl.getTimeInMillis();
    }

    public static Integer getLeftSeconds(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date condition = sdf.parse(date);
        long n = condition.getTime();
        long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
        // System.out.println("开始时间:"+date+"-->"+(int)((s-n)/1000));
        return (int) ((s - n) / 1000);
    }

    /**
     * 得到两个日期之间的日期list
     *
     * @param beginDate beginDate
     * @param endDate   endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        while (true) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * 日期加上几个月
     *
     * @param date  date
     * @param month month
     * @return Date
     */
    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

}
