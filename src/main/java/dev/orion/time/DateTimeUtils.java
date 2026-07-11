package dev.orion.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateTimeUtils {

    private DateTimeUtils(){}
    private static final DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter yyyyMMddHHmmssFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime add(LocalDateTime dateTime, TimeSetting setting){
        if (null == dateTime || null == setting){
            throw  new IllegalArgumentException("DateTime and TimeSetting cannot be null.");
        }
        return dateTime.plus(setting.value(), setting.unit());
    }

    public static LocalDateTime subtract(LocalDateTime dateTime, TimeSetting setting){
        if (null == dateTime || null == setting){
            throw  new IllegalArgumentException("DateTime and TimeSetting cannot be null.");
        }
        return dateTime.minus(setting.value(), setting.unit());
    }

    public static Date toDate(LocalDateTime dateTime){
        if (null == dateTime)
            throw new IllegalArgumentException("Date Time cannot be null");
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date){
        if(null == date)
            throw new IllegalArgumentException("Date cannot be null");
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static String yyyyMMdd(LocalDate date){
        if (null == date){
            throw new IllegalArgumentException("Date cannot be null");
        }
        return date.format(yyyyMMddFormatter);
    }

    public static String yyyyMMddHHmmss(LocalDateTime date){
        if (null == date){
            throw new IllegalArgumentException("Date cannot be null");
        }
        return date.format(yyyyMMddHHmmssFormatter);
    }
}
