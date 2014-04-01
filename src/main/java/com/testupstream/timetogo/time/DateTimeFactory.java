package com.testupstream.timetogo.time;

import org.joda.time.DateTime;

public class DateTimeFactory {

    private static DateTime date = DateTime.now();

    public static void freezeTime(DateTime dateTime) {
        date = dateTime;
    }

    public static void unfreezeTime() {
        date = DateTime.now();
    }

    public static DateTime now() {
        return date;
    }

}
