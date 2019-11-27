package com.example.ghost.travreldiary.Models;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ghost.travreldiary.R;

import java.util.ArrayList;

public class TripsAdapter extends ArrayAdapter<Trip> {

    private LayoutInflater layoutInflater;
    private OnItemClickedListener listener;
    private Resources resources;
    private String packageName;



    public TripsAdapter(Context context, ArrayList<Trip> trips, OnItemClickedListener listener) {
        super(context, 0,trips);
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
       // resources = context.getResources();
        packageName = context.getPackageName();
    }

    public View getView (final int position , View convertView, ViewGroup parent){

        RelativeLayout relativeLayout = (RelativeLayout)layoutInflater.inflate(R.layout.item_trip,null);


        ImageButton imageButton = (ImageButton)relativeLayout.findViewById(R.id.imageItem_1);
        ImageButton imageButton2 = (ImageButton)relativeLayout.findViewById(R.id.imageItem_2);
        ImageButton imageButton3 = (ImageButton)relativeLayout.findViewById(R.id.imageItem_3);
        TextView textViewTitle = (TextView) relativeLayout.findViewById(R.id.txt_nameTitle);
        TextView textViewDate = (TextView) relativeLayout.findViewById(R.id.txt_date);
        TextView textViewDes = (TextView) relativeLayout.findViewById(R.id.txt_des);
        ImageButton btnDelete = (ImageButton) relativeLayout.findViewById(R.id.imgDelete);



        Trip trip = getItem(position);

      //  int imageId = resources.getIdentifier(trip.getTitleOfTrip().toLowerCase(),"drawable",packageName);
        //int imageId2 = resources.getIdentifier(trip.getDestination().toString().toLowerCase(),"drawable",packageName);
        //int imageId3 = resources.getIdentifier(trip.getDestination().toString().toLowerCase(),"drawable",packageName);

        imageButton.setImageBitmap(trip.getPhoto());
        //imageButton.setImageResource(imageId2);
        //imageButton.setImageResource(imageId3);

        textViewTitle.setText(trip.getTitleOfTrip());
        textViewDate.setText(trip.getDateFrom()+" - "+trip.getDateTo());
        //textViewDes.setText(trip.getTripLocations().toString());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteProduct(position);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click_imageTrip(position);
            }
        });


        return relativeLayout;


    }

    public interface OnItemClickedListener{

        void deleteProduct(int position);
        void click_imageTrip(int position);

    }



}
