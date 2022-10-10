package com.example.mini_project.LOG_SIGN;


import android.app.Application;

import androidx.room.Room;

import com.example.mini_project.DbUtils.LocalDB;

public class RoomImplementaion extends Application {
    private static RoomImplementaion mInstance;
    private LocalDB dbInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        dbInstance = Room.databaseBuilder(getApplicationContext(),
                LocalDB.class, "LocalDB").build();
    }

    public static RoomImplementaion getmInstance() {
        return mInstance;
    }

    public  LocalDB getDbInstance() {
        return dbInstance;
    }
}