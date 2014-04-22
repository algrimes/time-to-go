package com.testupstream.timetogo.time;

import org.joda.time.DateTime;

public class DateTimeFactory {

    private static DateTime date;

    public static void freezeTime(DateTime dateTime) {
        date = dateTime;
    }

    public static DateTime now() {
        return date != null ? date : DateTime.now();
    }

}
