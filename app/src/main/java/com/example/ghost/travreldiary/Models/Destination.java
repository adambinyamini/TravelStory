package com.example.ghost.travreldiary.Models;

import java.util.ArrayList;

public class Destination  {

    public static ArrayList<String> getDestination(){
        ArrayList <String> destination = new ArrayList();
        destination.add("North_America");
        destination.add("South_America");
        destination.add("Europe");
        destination.add("Asia");
        destination.add("Africa");
        destination.add("Australia");

        return destination;

    }
}
