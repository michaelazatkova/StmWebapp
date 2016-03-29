package com.projects.savethemeeting.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Michaela on 22.03.2016.
 */
public class Util {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    public static String getDateString(Date date) {
        return sdf.format(date);
    }

    public static Date getDateFromString(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp getTimestampFromString(String datetime) {
        Date date = getDateFromString(datetime);
        return new Timestamp(date.getTime());
    }
}
