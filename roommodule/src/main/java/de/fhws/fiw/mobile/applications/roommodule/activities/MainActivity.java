package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.network.DownloadListener;
import de.fhws.fiw.mobile.applications.roommodule.network.RoomDownloader;

public class MainActivity extends AppCompatActivity implements DownloadListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_to_timetable = (Button)findViewById(R.id.button_to_timetable);
        setOnClickListenerToTimetable(button_to_timetable);

        Button button_to_detail = (Button) findViewById(R.id.button_to_detail);
        setOnClickListenerToDetail(button_to_detail);

        Button button_to_overview = (Button) findViewById(R.id.button_to_overview);
        setOnClickListenerToOverview(button_to_overview);

        new RoomDownloader(this).execute();
    }

    private void setOnClickListenerToOverview(Button button_to_overview) {
        button_to_overview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
                startActivity(intent);
            };
        });
    }

    private void setOnClickListenerToTimetable(Button button_to_timetable){
        button_to_timetable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimetableActivity.class);
                startActivity(intent);
            };
        });
    }

    private void setOnClickListenerToDetail(Button button_to_detail) {
        button_to_detail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            };
        });
    }

    @Override
    public void onDownloadSuccess() {
        Toast.makeText(this, "Download Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDownloadError() {
        Toast.makeText(this, "Error during download", Toast.LENGTH_LONG).show();
    }
}