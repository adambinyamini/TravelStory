package com.example.ghost.travreldiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.travreldiary.Models.BackgroundTask;
import com.example.ghost.travreldiary.Models.Database;
import com.example.ghost.travreldiary.Models.MyApp;
import com.example.ghost.travreldiary.Models.Trip;
import com.example.ghost.travreldiary.Models.TripLocation;
import com.example.ghost.travreldiary.R;

import java.io.FileInputStream;

public class WriteStoryActivity extends AppCompatActivity {

    private TextView txtLocation;
    private TextView txtTrip;
    private TextView txtDate;
    private RatingBar ratingBar;
    private Database database;
    private EditText txtStory;
    private String dateRangeFrom,dateRangeTo;
    private Bitmap bitmap;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_story);

        txtLocation = (TextView) findViewById(R.id.txt_locStory);
        txtTrip = (TextView) findViewById(R.id.txt_titleOfTrip_story);
        txtDate = (TextView) findViewById(R.id.txt_dateRange);
        txtStory = (EditText) findViewById(R.id.editTextDiary);
        imageView = (ImageView) findViewById(R.id.img_writeStory);
        ratingBar =(RatingBar)findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(WriteStoryActivity.this,"Value is: " + rating,Toast.LENGTH_LONG).show();
            }
        });

        if(database == null){
            database = new Database(this);
        }
        Intent intent = getIntent();
        String tripName = intent.getStringExtra("Trip");
        TripLocation tripLocation =(TripLocation) intent.getSerializableExtra("LocationName");
        //byte[] byteArray = getIntent().getByteArrayExtra("BitmapImage");
        //bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        bitmap = null;
        String filename = getIntent().getStringExtra("BitmapImage");
        try {
            FileInputStream is = this.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dateRangeFrom = intent.getStringExtra("strFrom");
        dateRangeTo = intent.getStringExtra("strTo");
        txtTrip.setText(tripName);
        txtLocation.setText(tripLocation.getName());
        txtDate.setText(dateRangeFrom + "-" +dateRangeTo);
        imageView.setImageBitmap(bitmap);


    }

    public void click_complete(View view) {
        Intent intent = new Intent(WriteStoryActivity.this,MyTripsActivity.class);
        Trip trip2 = new Trip(txtTrip.getText().toString(),dateRangeFrom,dateRangeTo);
        TripLocation tripLocation =new TripLocation(txtLocation.getText().toString(),txtStory.getText().toString());
        String type = "addTrip";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,txtTrip.getText().toString(),dateRangeFrom,dateRangeTo);
        database.addTrip(trip2);
        database.addLocation(tripLocation);
        startActivity(intent);


    }
    public void cancel_onClick(View view) {

        finish();
    }
}
