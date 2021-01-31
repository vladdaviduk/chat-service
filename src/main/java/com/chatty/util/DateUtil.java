package com.chatty.util;

import com.chatty.exceptions.ChattyRuntimeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {}

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String fromDate(Date date) {
        if (date == null) return null;

        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null) return null;

        try {
            return new Date(new SimpleDateFormat(DATE_FORMAT).parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new ChattyRuntimeException(String.format("Date value should be presented " +
                    "in the following format '%s' (provided - '%s'", DATE_FORMAT, dateStr));
        }
    }

}
