package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.helper.DpPixelConverter;
import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 26.05.2016.
 */
public class TimetableActivity extends AppCompatActivity {

    private FrameLayout timetable_layout;

    private Room room;

    private int sizeOfAnQuarterHourInDp;

    private int sizeOfFiveMinutesInDp;

    private final int TIMETABLE_BEGINS_AT_HOUR = 8;

    private final int LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP = 64;

    private final int RIGHT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP = 16;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        this.timetable_layout = (FrameLayout) findViewById(R.id.timetable_framelayout_id);
        setSizeOfAnQuarterHourInDp();
        setSizeOfFiveMinutesInDp();
        loadRoomData();
        iterateOverLectures();

    }

    private void loadRoomData() {
        this.room = new RoomTestData().getTestRoom();
    }

    private void iterateOverLectures() {

        displayCurrentTimeLine();

        for (Lecture lecture : this.room.getListOfLectures()) {

            LinearLayout newView = createNewTimetableEntry(lecture);

            addViewToTimetable(newView);

            addMarginTopToView(newView, lecture);

        }
    }

    private int calculateMarginTop(Lecture lecture) {

        int marginTopDp = 0;

        int startHourOfTimetable = this.TIMETABLE_BEGINS_AT_HOUR;

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

    private int calculateHeightOfTimeTableEntry(Lecture lecture){

        int heightDp = 0;

        int startHourOfLecture = TimeFormatter.getHourFromDate(lecture.getStartOfLecture());

        int startMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getStartOfLecture());

        int endHourOfLecture = TimeFormatter.getHourFromDate(lecture.getEndOfLecture());

        int endMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getEndOfLecture());

        while(!((startHourOfLecture == endHourOfLecture) && (startMinutesOfLecture == endMinutesOfLecture))){

            startMinutesOfLecture = (startMinutesOfLecture + 15) % 60;

            if(startMinutesOfLecture == 0){
                startHourOfLecture++;
            }

            heightDp += this.sizeOfAnQuarterHourInDp;
        }

        return heightDp;
    }

    private int calculateDifference(int startHourOfLecture, int timetableBeginsAtHour) {
        return startHourOfLecture - timetableBeginsAtHour;
    }

    private float getScreenWidthInDp() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }

    private void addMarginTopToView(View newView, Lecture lecture) {

        int marginTopInPx = DpPixelConverter.dpToPixels(this, calculateMarginTop(lecture));

        int widthInDp = (int) getScreenWidthInDp() - LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP - RIGHT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP;
        int widthInPx = DpPixelConverter.dpToPixels(this, widthInDp);

        int heightInPx = DpPixelConverter.dpToPixels(this, calculateHeightOfTimeTableEntry(lecture) );

        int leftMarginInPx = DpPixelConverter.dpToPixels(this, LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP);

        FrameLayout.LayoutParams layoutParamsOfView = (FrameLayout.LayoutParams) newView.getLayoutParams();
        layoutParamsOfView.leftMargin = leftMarginInPx;
        layoutParamsOfView.height = heightInPx;
        layoutParamsOfView.width = widthInPx;
        layoutParamsOfView.gravity = Gravity.TOP;
        layoutParamsOfView.topMargin = marginTopInPx;
    }

    private void addViewToTimetable(View newView) {
        LinearLayout.LayoutParams layoutParams = createLinearLayoutParamsForNewView();

        this.timetable_layout.addView(newView, layoutParams);
    }

    private void setSizeOfAnQuarterHourInDp() {
        int sizeOfQuarterHourInDp = (int) ((getResources().getDimension(R.dimen.timetable_grid_hour_size) /
                getResources().getDisplayMetrics().density) / 4);
        this.sizeOfAnQuarterHourInDp = sizeOfQuarterHourInDp;
    }

    private void setSizeOfFiveMinutesInDp() {
        int sizeOfFiveMinutesInDp = (int) ((getResources().getDimension(R.dimen.timetable_grid_hour_size) /
                getResources().getDisplayMetrics().density) / 12);
        this.sizeOfFiveMinutesInDp = sizeOfFiveMinutesInDp;
    }

    private LinearLayout createNewTimetableEntry(Lecture lecture) {

        LinearLayout newView = new LinearLayout(getBaseContext());
        newView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        newView.setElevation(8);

        TextView textView = new TextView(this);
        textView.setText(lecture.getLectureName());
        textView.setTextColor(Color.WHITE);

        newView.addView(textView);

        return newView;
    }

    private LinearLayout.LayoutParams createLinearLayoutParamsForNewView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        return params;
    }

    private void displayCurrentTimeLine(){

        LinearLayout timelineLayout = createTimeline();

        addViewToTimetable(timelineLayout);

        setLayoutParametersOfTimeline(timelineLayout);
    }

    private int calculateMarginTopOfTimeline(){
        Calendar currentTime = TimeFormatter.getCurrentTimeAsCalendar();

        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);

        int currentMinutes = currentTime.get(Calendar.MINUTE);

        int startHourOfTimetable = this.TIMETABLE_BEGINS_AT_HOUR;

        int differenceInHours = calculateDifference(currentHour, startHourOfTimetable);

        int marginTopDp = 0;

        while(differenceInHours > 0){
            marginTopDp += this.sizeOfAnQuarterHourInDp * 4;
            differenceInHours--;
        }

        while(currentMinutes > 4){
            marginTopDp += this.sizeOfFiveMinutesInDp;
            currentMinutes -= 5;
        }

        return marginTopDp;
    }

    private LinearLayout createTimeline() {
        LinearLayout newView = new LinearLayout(getBaseContext());
        newView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        newView.setElevation(8);
        return newView;
    }

    private void setLayoutParametersOfTimeline(LinearLayout timelineLayout){
        int marginTopDp = calculateMarginTopOfTimeline();

        FrameLayout.LayoutParams layoutParamsOfView = (FrameLayout.LayoutParams) timelineLayout.getLayoutParams();
        layoutParamsOfView.height = DpPixelConverter.dpToPixels(this, 4);
        layoutParamsOfView.width = DpPixelConverter.dpToPixels(this, (int) getScreenWidthInDp());
        layoutParamsOfView.topMargin = DpPixelConverter.dpToPixels(this, marginTopDp);
    }

    private int getHourOfDate(Date date) {
        return TimeFormatter.getHourFromDate(date);
    }

    private int getMinutesOfDate(Date date) {
        return TimeFormatter.getMinutesFromDate(date);
    }
}