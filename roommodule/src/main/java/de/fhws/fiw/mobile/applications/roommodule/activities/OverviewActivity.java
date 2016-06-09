package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.fhws.fiw.mobile.applications.roommodule.R;

/**
 * Created by Patrick Müller on 09.06.2016.
 */
public class OverviewActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
    }
}