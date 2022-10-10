package com.example.mini_project.DbUtils;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void createUser(User user);

    @Query("SELECT * FROM user where Email like :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM user where NickName like :NickName")
    User getUserByNickName(String NickName);

    @Query("SELECT * FROM user")
    User[] getUsers();


}
