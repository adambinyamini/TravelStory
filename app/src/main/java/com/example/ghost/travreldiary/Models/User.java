package com.example.ghost.travreldiary.Models;

import com.example.ghost.travreldiary.Models.Trip;

import java.util.ArrayList;

public class User {

    private String name;
    private String email;
    private String password;
    private String gender;
    private ArrayList<Trip> trips;

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.trips = new ArrayList<Trip>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void AddTrip(Trip trip) {
        this.trips.add(trip);
    }

    public void RemoveTrip(Trip trip) {
        this.trips.remove(trip);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
