package com.example.ghost.travreldiary.Models;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSP {
    Context context;
    SharedPreferences sharedPreferences;
    private String name;


    public UserSP(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo",context.MODE_PRIVATE);

    }

    public void removeUser(){
        sharedPreferences.edit().clear().commit();
    }


    public String getName() {
        name = sharedPreferences.getString("userdata","");
        return name;
    }


    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("userdata",name).commit();
    }
}
