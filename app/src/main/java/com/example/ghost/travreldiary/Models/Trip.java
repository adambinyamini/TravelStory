package com.example.ghost.travreldiary.Models;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Trip implements Parcelable {

    private int id;
    private String titleOfTrip ;
    private String dateFrom;
    private String dateTo;
    private Bitmap photo;
    private String description;
    private ArrayList<TripLocation> tripLocations;


    public Trip(int id ,String titleOfTrip,String dateFrom,String dateTo){
        this.titleOfTrip = titleOfTrip;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        setId(id);
    }

    public Trip(String titleOfTrip,String dateFrom,String dateTo) {
        this.titleOfTrip = titleOfTrip;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.photo = photo;
        this.tripLocations = new ArrayList<TripLocation>();
    }

    ///////bitmap Test
    public Trip(int id ,String titleOfTrip,String dateFrom,String dateTo,Bitmap photo){
        this.titleOfTrip = titleOfTrip;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.photo = photo;
        setId(id);
    }

    public Trip(String titleOfTrip,String dateFrom,String dateTo,Bitmap photo) {
        this.titleOfTrip = titleOfTrip;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.photo = photo;
        this.tripLocations = new ArrayList<TripLocation> ();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleOfTrip() {
        return titleOfTrip;
    }

    public void setTitleOfTrip(String name) {
        this.titleOfTrip = name;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<TripLocation> getTripLocations() {
        return tripLocations;
    }

    public void AddTripLocation(TripLocation tripLocation) {
        this.tripLocations.add(tripLocation);
    }

    public void RemoveTripLocation(TripLocation tripLocation) {
        this.tripLocations.remove(tripLocation);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Parcelable
    //-------------------------------------------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleOfTrip);

    }
    protected Trip(Parcel in) {
        //id = in.readInt();
        titleOfTrip = in.readString();
        //date = in.readString();
        //photo = in.readInt();
        //description = in.readString();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };
}
