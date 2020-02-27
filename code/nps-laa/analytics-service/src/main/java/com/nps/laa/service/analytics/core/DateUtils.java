package com.nps.laa.service.analytics.core;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    private DateUtils() {
    }

    public static LocalDate[] getWeekdays(LocalDate date) {
        int DAYS_OF_WEEK = 7;

        if (date == null) {
            date = LocalDate.now();
        }

        LocalDate begin = null;
        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            begin = date;
        } else {
            begin = date.minusDays(date.getDayOfWeek().getValue());
        }
        LocalDate end = begin.plusDays(DAYS_OF_WEEK - 1);

        LocalDate localDate[] = {begin, end};

        return localDate;

    }
}
