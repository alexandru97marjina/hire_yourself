package com.marjina.hire_yourself.common.util;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

    /**
     * Calculates number of months between dates
     *
     * @param date1 Start Date
     * @param date2 End Date
     * @return number of months
     */
    public static Integer getMonthDifference(Date date1, Date date2) {
        DateTime dateTime1 = new DateTime(date1);
        DateTime dateTime2 = new DateTime(date2);

        return Months.monthsBetween(dateTime1, dateTime2).getMonths();
    }

}
