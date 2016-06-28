package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    private int sizeOfAnHourInDp;

    private final int TIMETABLE_BEGINS_AT_HOUR = 8;

    private final int WIDTH_OF_TIMETABLE_ENTRY = 240;

    private final int LEFT_MARGIN_OF_TIMETABLE_ENTRY = 64;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        this.timetable_layout = (FrameLayout) findViewById(R.id.timetable_framelayout_id);
        saveSizeOfAnHourInDp();
        loadRoomData();
        iterateOverLectures();

    }

    private void loadRoomData() {
        this.room = new RoomTestData().getTestRoom();
    }

    private void iterateOverLectures() {

        for (Lecture lecture : this.room.getListOfLectures()) {

            LinearLayout newView = createNewTimetableEntry();

            TextView textView = new TextView(this);
            textView.setText("Grundlagen Informatik");
            textView.setTextColor(Color.WHITE);
            newView.addView(textView);

            addViewToTimetable(newView);

            int marginTop = calculateMarginTop(lecture);

            addMarginTopToView(newView, marginTop);

        }
    }

    private int calculateMarginTop(Lecture lecture) {

        int marginTop = 0;

        int startHourOfLecture = TimeFormatter.getHourFromDate(lecture.getStartOfLecture());

        int startMinutesOfLecture = TimeFormatter.getMinutesFromDate(lecture.getStartOfLecture());

        int differenceInHours = calculateDifference(startHourOfLecture, this.TIMETABLE_BEGINS_AT_HOUR);

        while (differenceInHours > 0) {
            marginTop += this.sizeOfAnHourInDp;
            differenceInHours--;
        }

        //für viertelstunden noch machen

        return marginTop;
    }

    private int calculateDifference(int startHourOfLecture, int timetableBeginsAtHour) {
        return startHourOfLecture - timetableBeginsAtHour;
    }

    private void addMarginTopToView(View newView, int marginTop) {

        int marginTopInPx = DpPixelConverter.dpToPixels(this, marginTop);

        int widthInPx = DpPixelConverter.dpToPixels(this, WIDTH_OF_TIMETABLE_ENTRY);

        int heightInPx = DpPixelConverter.dpToPixels(this, 40); //TODO

        int leftMarginInPx = DpPixelConverter.dpToPixels(this, LEFT_MARGIN_OF_TIMETABLE_ENTRY);

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

    private void saveSizeOfAnHourInDp() {
        int sizeOfHourInDp = (int) (getResources().getDimension(R.dimen.timetable_grid_hour_size) /
                getResources().getDisplayMetrics().density);
        this.sizeOfAnHourInDp = sizeOfHourInDp;
    }

    private LinearLayout createNewTimetableEntry() {

        LinearLayout newView = new LinearLayout(getBaseContext());
        newView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        return newView;
    }

    private LinearLayout.LayoutParams createLinearLayoutParamsForNewView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        return params;
    }

    private int getHourOfDate(Date date) {
        return TimeFormatter.getHourFromDate(date);
    }

    private int getMinutesOfDate(Date date) {
        return TimeFormatter.getMinutesFromDate(date);
    }
}