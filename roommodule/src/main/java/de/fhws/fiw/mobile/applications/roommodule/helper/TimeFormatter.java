package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by christian on 20/06/16.
 */
public class TimeFormatter {

    public static long todayAsMilliseconds() {
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        calendar.clear();
        calendar.set(year, month, day);

        return calendar.getTimeInMillis();
    }

    public static long tomorrowAsMilliseconds() {
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        calendar.clear();
        calendar.set(year, month, day+1);

        return calendar.getTimeInMillis();
    }
}
