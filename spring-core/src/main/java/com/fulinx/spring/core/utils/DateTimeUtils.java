/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeUtils {

    public static String format(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }
    public static LocalDateTime parse(String value, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(value, dateTimeFormatter);
    }
    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }
    public static LocalDateTime of(Long timeMillis, ZoneOffset zoneOffset){
        return Instant.ofEpochMilli(timeMillis).atZone(zoneOffset).toLocalDateTime();
    }
    public static LocalDateTime of(Long timeMillis){
        return of(timeMillis, ZoneOffset.ofHours(8));
    }
    public static Long toMilliSecond(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
    public static Long getCurrentMilliSecond(){
        return toMilliSecond(LocalDateTime.now());
    }

    public static long diffDays(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd){
        return localDateTimeEnd.toLocalDate().toEpochDay() - localDateTimeStart.toLocalDate().toEpochDay();
    }

    public static long diffMonths(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd){
        return localDateTimeStart.toLocalDate().until(localDateTimeEnd.toLocalDate(), ChronoUnit.MONTHS);
    }

    public static LocalDateTime getStartDayOfMonth(LocalDateTime localDateTime){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, localDateTime.getYear());
        calendar.set(Calendar.MONTH, localDateTime.getMonthValue() - 1);
        calendar.set(Calendar.DATE, 1);
        return of(calendar.getTime().getTime());
    }

    public static LocalDateTime getEndDayOfMonth(LocalDateTime localDateTime){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, localDateTime.getYear());
        calendar.set(Calendar.MONTH, localDateTime.getMonthValue() - 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return of(calendar.getTime().getTime());
    }

    public static List<LocalDateTime> severalDays(int month, int dateStart, int dateEnd){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, dateStart);
        LocalDateTime localDateTimeStart = of(calendar.getTime().getTime());
        calendar.set(Calendar.DATE, dateEnd);
        LocalDateTime localDateTimeEnd = of(calendar.getTime().getTime());
        return severalDays(localDateTimeStart, localDateTimeEnd);
    }

    public static List<LocalDateTime> severalDays(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        return severalDays(month, calendar.getActualMinimum(Calendar.DATE), calendar.getActualMaximum(Calendar.DATE));
    }

    public static List<LocalDateTime> severalDays(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd){
        return severalDays(localDateTimeStart, diffDays(localDateTimeStart, localDateTimeEnd));
    }

    public static List<LocalDateTime> severalDays(LocalDateTime baseLocalDateTime, long deltaDay){
        List<LocalDateTime> severalDayList = new ArrayList<>();
        if(deltaDay >= 0){
            for(int i = 0; i <= deltaDay; i ++){
                LocalDateTime localDateTime = baseLocalDateTime.plusDays(i);
                severalDayList.add(localDateTime);
            }
        }else {
            deltaDay = Math.abs(deltaDay);
            for(int i = 0; i <= deltaDay; i ++){
                LocalDateTime localDateTime = baseLocalDateTime.minusDays(i);
                severalDayList.add(localDateTime);
            }
        }
        return severalDayList;
    }
}
