package com.example.mini_project.DbUtils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database( entities = {User.class}, version= 1)
public abstract class LocalDB extends RoomDatabase {
    // DAOs
    public abstract UserDao userDao();

}
