package com.example.calendarview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        textView = (TextView) findViewById(R.id.textview);

        Calendar cal = Calendar.getInstance();
        int year = 0, month = 0, day = 0;

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        textView.setText(year + "년 " + month + "월 " + day + "일");

        CalendarView.OnDateChangeListener mDateChangeListener = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {

                textView.setText(new String().format("year %d: month:%d, day:%d", i, i1+1, i2));
            }
        };
        calendarView.setOnDateChangeListener(mDateChangeListener);
    }
    public void chronoStartClick(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setTextColor(Color.rgb(0,0,0));
        chronometer.start();
    }
    public void chronoStopClick(View v){
        chronometer.stop();
        chronometer.setTextColor(Color.rgb(255,0,0));
    }
}