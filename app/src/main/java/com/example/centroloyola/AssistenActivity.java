package com.example.centroloyola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class AssistenActivity extends AppCompatActivity {


    CalendarView calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assisten);

        calendario =(CalendarView)findViewById(R.id.calendarView);

       calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                Toast.makeText(AssistenActivity.this,dayOfMonth + "/" + month + "/" + year,Toast.LENGTH_LONG).show();
            }
        });
    }
}
