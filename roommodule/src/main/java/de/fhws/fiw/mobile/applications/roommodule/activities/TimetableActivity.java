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

import de.fhws.fiw.mobile.applications.roommodule.R;

/**
 * Created by Patrick Müller on 26.05.2016.
 */
public class TimetableActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.timetable_framelayout_id);

        View mView = new View(getBaseContext());
        mView.setBackgroundColor(Color.BLACK);


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(mView, params);

        Log.d("TimetableActivity", "Die Farbe des Buttons: " + mView.getDrawingCacheBackgroundColor());

        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());


        ViewGroup.LayoutParams layoutParamsOfView = mView.getLayoutParams();
        layoutParamsOfView.height = (int) px;
        layoutParamsOfView.width = (int) px;
    }
}
