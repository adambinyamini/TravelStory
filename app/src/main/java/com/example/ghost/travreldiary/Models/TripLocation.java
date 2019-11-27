package com.example.ghost.travreldiary.Models;

import java.io.Serializable;
import java.util.Date;

public class TripLocation implements Serializable {

    private int id;
    private String name;
    private int photo;
    private Date from;
    private Date to;
    private String story;

    public TripLocation(String name ){
        this.name = name;
        setId(id);
    }

    public TripLocation(String name,String story ){
        this.name = name;
        this.story = story;
        setId(id);
    }

    public TripLocation(String name, int photo, Date from, Date to, String story) {
        this.name = name;
        this.photo = photo;
        this.story = story;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}