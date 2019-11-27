package com.example.ghost.travreldiary.Activities;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ghost.travreldiary.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class DateRangeActivity extends AppCompatActivity  {

    private TextView txtTitle;
    private ArrayList<String> dates = new ArrayList<>();
    private DatePickerDialog datePicker1;
    private Calendar calendar;
    private Button btnFrom,btnTo;
    private ImageButton btnNext;
    private String strFrom,strTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        txtTitle = (TextView) findViewById(R.id.txt_titleOfCalender);
        btnFrom = (Button)findViewById(R.id.btn_from);
        btnTo = (Button)findViewById(R.id.btn_to);
        btnNext = (ImageButton)findViewById(R.id.btn_nextDateRange);


        Intent intent = getIntent();
        String titleOfTrip = intent.getStringExtra("Trip");
        txtTitle.setText(titleOfTrip);

}

    public void click_nextToDestination(View view) {
        Intent intent = new Intent(DateRangeActivity.this, LocationActivity.class);
        intent.putExtra("Trip",txtTitle.getText().toString() );
        intent.putExtra("strFrom",strFrom);
        intent.putExtra("strTo",strTo);
        startActivity(intent);
    }

    public void cancel_onClick(View view) {
        finish();
    }

    public void click_from(View view) {

        final Calendar myCalendar = Calendar.getInstance();
        int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        int mMonth = myCalendar.get(Calendar.MONTH);
        int mYear = myCalendar.get(Calendar.YEAR);

        datePicker1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                strFrom = dayOfMonth + "/" + (month+1) + "/" + year;

               // if (dates.size()<=1){
              //  dates.add(strFrom);
                btnFrom.setText("From: "+ strFrom);}
               // else {
                 //   dates.clear();
                //};
           // }
        }, mYear, mMonth, mDay);
        datePicker1.updateDate(2019,05,01);
        datePicker1.show();

    }


    public void click_to(View view) {
        final Calendar myCalendar = Calendar.getInstance();
        int mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        int mMonth = myCalendar.get(Calendar.MONTH);
        int mYear = myCalendar.get(Calendar.YEAR);

        datePicker1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                strTo = dayOfMonth + "/" + (month+1) + "/" + year;

              //  if (dates.size()<=1){
                //    dates.add(strTo);
                    btnTo.setText("To: "+ strTo);
                  //  }
               /// else {
                  //  dates.clear();
                //};
            }
        }, mYear, mMonth, mDay);
        datePicker1.updateDate(2019,05,01);
        datePicker1.show();

    }

}