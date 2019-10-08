package com.marjina.hire_yourself.common.util;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;

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

    /**
     * Get Timestamp from date and time in valid format
     *
     * @param date DateTime in valid format
     * @return Timestamp of converted DateTIme
     */
    public static Timestamp parseDateTimeToTimestamp(String date) throws ParseException {
        DateTime dateTime = new DateTime(buildDateTime(date));
        dateTime = dateTime.withSecondOfMinute(0).withMinuteOfHour(0);

        return new Timestamp(dateTime.getMillis());
    }

    /**
     * Build Date object from String Date and String Time
     *
     * @param dateTime String dateTime
     * @return built Date from date and time
     */
    public static Date buildDateTime(String dateTime) throws ParseException {
        Date newDate;

        try {
            newDate = new SimpleDateFormat(FULL_DATE_TIME_FORMAT).parse(dateTime);
        } catch (ParseException e) {
            newDate = new SimpleDateFormat(HALF_DATE_TIME_FORMAT).parse(dateTime);
        }

        return newDate;
    }

}
