package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.activities.RoomTestData;
import de.fhws.fiw.mobile.applications.roommodule.activities.TimetableView;
import de.fhws.fiw.mobile.applications.roommodule.helper.DpPixelConverter;
import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 30.06.2016.
 */
public class TimetableFragment extends Fragment {

    private FrameLayout rootView;

    private Room room;

    private int sizeOfAnQuarterHourInDp;

    private int sizeOfFiveMinutesInDp;

    private final int TIMETABLE_BEGINS_AT_HOUR = 8;

    private final int TIMETABLE_MARGIN_TOP_DP = 16;

    private final int LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP = 64;

    private final int RIGHT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP = 16;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        this.rootView = (FrameLayout) inflater.inflate(R.layout.drawed_timetable, parent, false);

        TimetableView timetableView = new TimetableView(getContext(), null);
        timetableView.setText("Hallo");

        ScrollView scrollView = (ScrollView)this.rootView.findViewById(R.id.scrollview_drawed_timetable);
        scrollView.addView(new TimetableView(getContext(), null));

//        setSizeOfAnQuarterHourInDp();
//        setSizeOfFiveMinutesInDp();
//        loadRoomData();
//        iterateOverLectures();

//        getFragmentManager().beginTransaction()
//                .add(R.id.timetable_entry_fragment, new TimetableFragment())
//                .commit();

        return this.rootView;
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

    private void loadRoomData() {
        this.room = new RoomTestData().getTestRoom();
    }

    private void iterateOverLectures() {

        displayCurrentTimeLine();

        for (Lecture lecture : this.room.getListOfLectures()) {

            LinearLayout newView = createNewTimetableEntry(lecture);

            addViewToTimetable(newView);

            setLayoutParametersOfTimetableEntry(newView, lecture);

        }
    }

    private void displayCurrentTimeLine(){

        LinearLayout timelineLayout = createTimeline();

        addViewToTimetable(timelineLayout);

        setLayoutParametersOfTimeline(timelineLayout);
    }

    private LinearLayout createTimeline() {
        LinearLayout newView = new LinearLayout(this.getContext());
        newView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        newView.setElevation(4);
        return newView;
    }

    private void addViewToTimetable(View newView) {
        LinearLayout.LayoutParams layoutParams = createLinearLayoutParamsForNewView();

        this.rootView.addView(newView, layoutParams);
    }

    private LinearLayout.LayoutParams createLinearLayoutParamsForNewView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        return params;
    }

    private void setLayoutParametersOfTimeline(LinearLayout timelineLayout){
        int marginTopDp = calculateMarginTopOfTimeline();

        FrameLayout.LayoutParams layoutParamsOfView = (FrameLayout.LayoutParams) timelineLayout.getLayoutParams();
        layoutParamsOfView.height = DpPixelConverter.dpToPixels(this.getActivity(), 4);
        layoutParamsOfView.width = DpPixelConverter.dpToPixels(this.getActivity(), (int) getScreenWidthInDp());
        layoutParamsOfView.topMargin = DpPixelConverter.dpToPixels(this.getActivity(), marginTopDp);
    }

    private int calculateMarginTopOfTimeline(){
        Calendar currentTime = TimeFormatter.getCurrentTimeAsCalendar();

        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);

        int currentMinutes = currentTime.get(Calendar.MINUTE);

        int startHourOfTimetable = this.TIMETABLE_BEGINS_AT_HOUR;

        int differenceInHours = calculateDifference(currentHour, startHourOfTimetable);

        int marginTopDp = TIMETABLE_MARGIN_TOP_DP;

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

    private float getScreenWidthInDp() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }

    private LinearLayout createNewTimetableEntry(Lecture lecture) {

        LinearLayout newView = new LinearLayout(this.getContext());
        newView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        newView.setElevation(8);

        TextView textView = new TextView(this.getContext());
        textView.setText(lecture.getLectureName());
        textView.setTextColor(Color.WHITE);

        newView.addView(textView);

        setLayoutParametersOfTextView(textView);

        return newView;
    }

    private void setLayoutParametersOfTextView(View textView){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 8, 0, 0);
        textView.setLayoutParams(layoutParams);
    }

    private void setLayoutParametersOfTimetableEntry(View newView, Lecture lecture) {

        int marginTopInPx = DpPixelConverter.dpToPixels(this.getActivity(), calculateMarginTopOfTimetableEntry(lecture));

        int widthInDp = (int) getScreenWidthInDp() - LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP - RIGHT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP;
        int widthInPx = DpPixelConverter.dpToPixels(this.getActivity(), widthInDp);

        int heightInPx = DpPixelConverter.dpToPixels(this.getActivity(), calculateHeightOfTimeTableEntry(lecture) );

        int leftMarginInPx = DpPixelConverter.dpToPixels(this.getActivity(), LEFT_MARGIN_OF_TIMETABLE_ENTRY_IN_DP);

        FrameLayout.LayoutParams layoutParamsOfView = (FrameLayout.LayoutParams) newView.getLayoutParams();
        layoutParamsOfView.leftMargin = leftMarginInPx;
        layoutParamsOfView.height = heightInPx;
        layoutParamsOfView.width = widthInPx;
        layoutParamsOfView.gravity = Gravity.TOP;
        layoutParamsOfView.topMargin = marginTopInPx;
    }

    private int calculateMarginTopOfTimetableEntry(Lecture lecture) {

        int marginTopDp = TIMETABLE_MARGIN_TOP_DP;

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

    private int calculateDifference(int startHourOfLecture, int timetableBeginsAtHour) {
        return startHourOfLecture - timetableBeginsAtHour;
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
}