package com.example.ghost.travreldiary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.ghost.travreldiary.Models.Database;
import com.example.ghost.travreldiary.Models.Trip;
import com.example.ghost.travreldiary.Models.TripsAdapter;
import com.example.ghost.travreldiary.Models.UserSP;
import com.example.ghost.travreldiary.R;

import java.util.ArrayList;


 public class MyTripsActivity extends AppCompatActivity implements TripsAdapter.OnItemClickedListener {

    private ArrayList<Trip> myTrips;
    private TripsAdapter tripsAdapter;
    private ListView listViewTrips;
    private Database database;




     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        listViewTrips = (ListView) findViewById(R.id.listView_trips);

        if (database == null){
            database = new Database(this);
        }


        myTrips = database.getAllTrips();
        tripsAdapter = new TripsAdapter(this,myTrips,this);
        listViewTrips.setAdapter(tripsAdapter);



    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.menu,menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
             case R.id.item1:
                 new UserSP(this).removeUser();
                 Intent intent = new Intent(this,LoginActivity.class);
                 startActivity(intent);
                 finish();
                     return true;
                 default:
                     return super.onOptionsItemSelected(item);

         }
     }



     public void create_destination_click(View view) {
        Intent intent = new Intent(MyTripsActivity.this, TitleNameActivity.class);
        startActivity(intent);
    }

     @Override
     public void deleteProduct(final int position) {
         AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
         alertDialog.setTitle("Delete trip");
         alertDialog.setMessage("Are you sure you want to delete this trip?");
         alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface,int p  ){

                 database.deleteTrip(myTrips.get(position));
                 myTrips.remove(position);
                 listViewTrips.setAdapter(tripsAdapter);
             }
         });

         alertDialog.setNegativeButton(android.R.string.no,null);
         alertDialog.setIcon(R.drawable.ic_delete);
         alertDialog.show();

     }

     @Override
     public void click_imageTrip(int position) {
        Intent intent = new Intent(this,LocationGalleryActivity.class);
        startActivity(intent);

     }


 }
