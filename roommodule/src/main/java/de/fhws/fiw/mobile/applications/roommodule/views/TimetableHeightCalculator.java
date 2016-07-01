package de.fhws.fiw.mobile.applications.roommodule.views;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Calendar;

import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.views.TimetableConfig;

/**
 * Created by Patrick Müller on 01.07.2016.
 */
public class TimetableHeightCalculator {

    private Context context;

    private int sizeOfAnQuarterHourInDp;

    private int sizeOfFiveMinutesInDp;

    public TimetableHeightCalculator(Context context){

        this.context = context;

        calculateSizeOfAnQuarterHourInDp();

        calculateSizeOfFiveMinutesInDp();
    }

    private void calculateSizeOfAnQuarterHourInDp() {
        int sizeOfQuarterHourInDp = (int) (TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP / 4);
        this.sizeOfAnQuarterHourInDp = sizeOfQuarterHourInDp;
    }

    private void calculateSizeOfFiveMinutesInDp() {
        int sizeOfFiveMinutesInDp = (int) (TimetableConfig.TIMETABLE_DISTANCE_BETWEEN_TWO_LINES_IN_DP / 12);
        this.sizeOfFiveMinutesInDp = sizeOfFiveMinutesInDp;
    }

    public int calculateMarginTopOfTimeline() {
        Calendar currentTime = TimeFormatter.getCurrentTimeAsCalendar();

        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);

        int currentMinutes = currentTime.get(Calendar.MINUTE);

        int startHourOfTimetable = TimetableConfig.TIMETABLE_BEGINS_AT_HOUR;

        int differenceInHours = calculateDifference(currentHour, startHourOfTimetable);

        int marginTopDp = TimetableConfig.TIMETABLE_MARGIN_TOP_IN_DP;

        while (differenceInHours > 0) {
            marginTopDp += this.sizeOfAnQuarterHourInDp * 4;
            differenceInHours--;
        }

        while (currentMinutes > 4) {
            marginTopDp += this.sizeOfFiveMinutesInDp;
            currentMinutes -= 5;
        }

        return marginTopDp;
    }

    private float getScreenWidthInDp() {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }

    public int calculateMarginTopOfTimetableEntry(Lecture lecture) {

        int marginTopDp = TimetableConfig.TIMETABLE_MARGIN_TOP_IN_DP;

        int startHourOfTimetable = TimetableConfig.TIMETABLE_BEGINS_AT_HOUR;

        int startHourOfLecture = TimeFormatter.getHourFromDate(lecture.getStartOfLecture());

        int startMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getStartOfLecture());

        int differenceInHours = calculateDifference(startHourOfLecture, startHourOfTimetable);

        while (differenceInHours > 0) {
            marginTopDp += this.sizeOfAnQuarterHourInDp * 4;
            differenceInHours--;
        }

        while (startMinutesOfLecture > 0) {
            marginTopDp += this.sizeOfAnQuarterHourInDp;
            startMinutesOfLecture -= 15;
        }

        return marginTopDp;
    }

    private int calculateDifference(int startHourOfLecture, int timetableBeginsAtHour) {
        return startHourOfLecture - timetableBeginsAtHour;
    }

    public int calculateHeightOfTimeTableEntry(Lecture lecture) {

        int heightDp = 0;

        int startHourOfLecture = TimeFormatter.getHourFromDate(lecture.getStartOfLecture());

        int startMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getStartOfLecture());

        int endHourOfLecture = TimeFormatter.getHourFromDate(lecture.getEndOfLecture());

        int endMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getEndOfLecture());

        while (!((startHourOfLecture == endHourOfLecture) && (startMinutesOfLecture == endMinutesOfLecture))) {

            startMinutesOfLecture = (startMinutesOfLecture + 15) % 60;

            if (startMinutesOfLecture == 0) {
                startHourOfLecture++;
            }

            heightDp += this.sizeOfAnQuarterHourInDp;
        }

        return heightDp;
    }
}
