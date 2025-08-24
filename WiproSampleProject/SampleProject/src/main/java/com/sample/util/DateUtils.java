package com.sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    public static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat(DEFAULT_FORMAT).parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date: " + dateStr, e);
        }
    }

    public static String formatDate(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
    }
}
