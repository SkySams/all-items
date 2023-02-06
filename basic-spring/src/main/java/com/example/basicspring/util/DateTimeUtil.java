package com.example.basicspring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author: zyh
 * @date: 2023/2/6
 */



public class DateTimeUtil {


    //输入一个时间,获取对应的自然月开始时间 和结束时间
    public static Map getMonthStartTimeByDate(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateTime);
        long currentTime = date.getTime();
        String timeZone = "GMT+8:00";
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long timeInMillis = calendar.getTimeInMillis();
        Date resultStartDate = new Date(timeInMillis);
        String resultStartTime = simpleDateFormat.format(resultStartDate);

        Calendar calendar2 = Calendar.getInstance();// 获取当前日期
        calendar2.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar2.setTimeInMillis(currentTime);
        calendar2.add(Calendar.YEAR, 0);
        calendar2.add(Calendar.MONTH, 0);
        calendar2.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        calendar2.set(Calendar.MILLISECOND, 999);
        long timeInMillis2 = calendar2.getTimeInMillis();
        Date resultEndDate = new Date(timeInMillis2);
        String resultEndTime = simpleDateFormat.format(resultEndDate);

        Map map = new HashMap();
        map.put("startDate", resultStartTime);
        map.put("endDate", resultEndTime);

        return map;
    }


    //输入一个时间,获取对应的自然年开始时间,结束时间
    public static Map getYearStartTimeByDate(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateTime);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(gc.get(1)));
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        Long dayStartTime = getDayStartTime(cal.getTime());
        Date resultStartDate = new Date(dayStartTime);
        String resultStartTime = simpleDateFormat.format(resultStartDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, Integer.valueOf(gc.get(1)));
        cal2.set(Calendar.MONTH, Calendar.DECEMBER);
        cal2.set(Calendar.DATE, 31);
        Long dayEndTime = getDayStartTime(cal2.getTime());
        Date resultEndDate = new Date(dayEndTime);
        String resultEndTime = simpleDateFormat.format(resultEndDate);
        Map map = new HashMap();
        map.put("startDate", resultStartTime);
        map.put("endDate", resultEndTime);
        return map;


    }


    public static Long getDayStartTime(Date d) {

        Calendar calendar = Calendar.getInstance();

        if (null != d) {
            calendar.setTime(d);
        }

        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();

    }

    public static void main(String[] args) throws Exception {

        Map monthStartTimeByDate = getMonthStartTimeByDate("2020-03-24");
        System.out.println(monthStartTimeByDate.toString());


        Map yearStartTimeByDate = getYearStartTimeByDate("2020-03-24");
        System.out.println(yearStartTimeByDate.toString());
    }


}