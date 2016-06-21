package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 26.05.2016.
 */
public class TimetableActivity extends AppCompatActivity {

    private FrameLayout timetable_layout;

    private Room room;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        this.timetable_layout = (FrameLayout) findViewById(R.id.timetable_framelayout_id);
        loadRoomData();
//        iterateOverLectures();

        View mView = new View(getBaseContext());
        mView.setBackgroundColor(Color.BLACK);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        timetable_layout.addView(mView, params);

        Log.d("TimetableActivity", "Die Farbe des Buttons: " + mView.getDrawingCacheBackgroundColor());

        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());


        ViewGroup.LayoutParams layoutParamsOfView = mView.getLayoutParams();
        layoutParamsOfView.height = (int) px;
        layoutParamsOfView.width = (int) px;
    }

    private void loadRoomData() {
        this.room = new RoomTestData().getTestRoom();
    }

    private void iterateOverLectures(){

        for(Lecture lecture : this.room.getListOfLectures()){

            createNewTimetableEntry();
        }
    }

    private View createNewTimetableEntry(){

        View mView = new View(getBaseContext());
        mView.setBackgroundColor(Color.BLACK);

        return mView;
    }

    private int getHourOfDate(Date date){
        return TimeFormatter.getHourFromDate(date);
    }

    private int getMinutesOfDate(Date date){
        return TimeFormatter.getMinutesFromDate(date);
    }
}