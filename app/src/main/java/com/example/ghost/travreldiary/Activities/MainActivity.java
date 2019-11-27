package com.example.ghost.travreldiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ghost.travreldiary.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button btnMyTrips;


    //Activity is not available !!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMyTrips = (Button) findViewById(R.id.buttonMyTrips);
        btnMyTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyTripsActivity.class);
                startActivity(intent);
            }


        });


      //  Intent intent = getIntent();
        //String textSignUp = intent.getStringExtra(SignUpActivity.EXTRA_TEXT);
        //TextView textNameUser = (TextView) findViewById(R.id.text_Name);
       // textNameUser.setText("Hi " + textSignUp);

    }


    public void write(View view) {

        intent();
    }

    public void intent() {
        Intent intent = new Intent(MainActivity.this, WriteDiaryActivity.class);
        startActivity(intent);
    }

}