package com.example.ghost.travreldiary.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    public Database(Context context){
        super(context,"TripsDatabase",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Trips(id INTEGER PRIMARY KEY AUTOINCREMENT,titleOfTrip TEXT NOT NULL,dateFrom TEXT NOT NULL,dateTo TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Locations(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,story TEXT NOT NULL)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


            db.execSQL("DROP TABLE Trips");
            db.execSQL("DROP TABLE Locations");
            onCreate(db);

    }


    public void addLocation(TripLocation tripLocation){

        String sql = String.format("INSERT INTO Locations(name,story) VALUES('%s','%s')"
                ,tripLocation.getName(),tripLocation.getStory());

        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.execSQL(sql);

        Cursor cursor = db1.rawQuery("SELECT last_insert_rowid()",null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        tripLocation.setId(id);
        cursor.close();
        close();

    }

    public void addTrip(Trip trip){

        String sql = String.format("INSERT INTO Trips(titleOfTrip,dateFrom,dateTo) VALUES('%s','%s','%s')"
                ,trip.getTitleOfTrip(),trip.getDateFrom(),trip.getDateTo());

        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.execSQL(sql);

        Cursor cursor = db1.rawQuery("SELECT last_insert_rowid()",null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        trip.setId(id);
        cursor.close();
        close();

    }

    public void deleteTrip(Trip trip){

        String sql = String.format("DELETE FROM Trips WHERE id=%d",trip.getId());

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(sql);

        close();

    }

    public ArrayList<Trip> getAllTrips() {

        ArrayList<Trip> trips = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Trips", null);

        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("titleOfTrip");
        int dateFromIndex = cursor.getColumnIndex("dateFrom");
        int dateToIndex = cursor.getColumnIndex("dateTo");

        while (cursor.moveToNext()) {

            int id = cursor.getInt(idIndex);
            String titleOfTrip = cursor.getString(nameIndex);
            String dateFrom = cursor.getString(dateFromIndex);
            String dateTo = cursor.getString(dateToIndex);

            Trip trip = new Trip(id, titleOfTrip,dateFrom,dateTo);
            trips.add(trip);

        }
        cursor.close();
        close();
        return trips;
    }
}
