package com.example.ghost.travreldiary.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.travreldiary.Models.Database;
import com.example.ghost.travreldiary.Models.TripLocation;
import com.example.ghost.travreldiary.R;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class LocationActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1;
    private static final int PERMISSION_CODE = 2;


    private EditText editTextDes;
    private TextView txtTitle;
    private String dateRangeFrom,dateRangeTo;
    private Database database;
    private ImageView imgGallery;
    private Uri imageUri;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dastination);

        editTextDes = (EditText)findViewById(R.id.editTxtDes);
        txtTitle = (TextView)findViewById(R.id.txt_titleOfDestination);
        imgGallery = (ImageView) findViewById(R.id.img_gallery);

        if (database == null){
            database = new Database(this);
        }

        Intent intent = getIntent();
        String titleOfTrip = intent.getStringExtra("Trip");
        dateRangeFrom = intent.getStringExtra("strFrom");
        dateRangeTo = intent.getStringExtra("strTo");

        txtTitle.setText(titleOfTrip);


    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                Log.i("myError",e.getMessage());
                e.printStackTrace();
            }
            imgGallery.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==
                        PackageManager.PERMISSION_DENIED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"Permissions denied",Toast.LENGTH_SHORT).show();
                    pickImageFromGallery();
                }
            }
        }
    }

    public void click_gallery(View view) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            if(checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                String []  permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_CODE);
            }
            else {
                pickImageFromGallery();
            }
        }
        else {pickImageFromGallery();}
    }

    public void click_nextToWrite(View view) {

        try {
            String filename = "bitmap.png";
            FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();
            bitmap.recycle();


            Intent intent = new Intent(LocationActivity.this,WriteStoryActivity.class);
            String locationName= editTextDes.getText().toString();
            TripLocation tripLocation = new TripLocation(locationName);
            intent.putExtra("Trip",txtTitle.getText().toString() );
            intent.putExtra("BitmapImage",filename);
            intent.putExtra("LocationName",tripLocation);
            intent.putExtra("strFrom",dateRangeFrom );
            intent.putExtra("strTo",dateRangeTo );
            startActivity(intent);

        } catch (Exception e) {
            Log.d("My Error",e.getMessage());
            e.printStackTrace();
        }
    }

    public void cancel_onClick(View view) {
        finish();
    }
}
