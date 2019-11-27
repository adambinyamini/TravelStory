package com.example.ghost.travreldiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ghost.travreldiary.Models.Destination;
import com.example.ghost.travreldiary.Models.Trip;
import com.example.ghost.travreldiary.R;

import java.io.Serializable;

public class TitleNameActivity extends AppCompatActivity {

    private EditText editTextTitleDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_name);



    }

    public void click_nextToDate(View view) {
        editTextTitleDes = (EditText)findViewById(R.id.editText_titleDes);
        String nameTitle = editTextTitleDes.getText().toString();
       // Trip trip2 = new Trip(nameTitle);
        Intent intent = new Intent(TitleNameActivity.this, DateRangeActivity.class);
        intent.putExtra("Trip",  nameTitle);
       // setResult(RESULT_OK,intent);
        startActivity(intent);

    }

    public void cancel_onClick(View view) {
        finish();
    }
}
