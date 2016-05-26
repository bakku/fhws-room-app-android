package de.fhws.fiw.mobile.applications.roommodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_to_timetable = (Button)findViewById(R.id.button_to_timetable);
//        setOnClickListenerToTimetable(button_to_timetable);
        button_to_timetable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimetableActivity.class);
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
}