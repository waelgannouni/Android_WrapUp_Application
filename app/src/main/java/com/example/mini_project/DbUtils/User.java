package com.example.mini_project.DbUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User{

    @PrimaryKey(autoGenerate = true)
    private int userid;

    @ColumnInfo(name = "NickName")
    private String NickName;

    @ColumnInfo(name = "Firstname")
    private String Firstname;

    @ColumnInfo(name = "Lastname")
    private String Lastname;

    @ColumnInfo(name = "Email")
    private String Email;


    @ColumnInfo(name = "password")
    private String password;

    public User() {
    }
    public User(String nickName, String firstname, String lastname, String email, String password) {
        this.userid = userid;
        NickName = nickName;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        this.password = password;
    }
    public User(int userid, String nickName, String firstname, String lastname, String email, String password) {
        this.userid = userid;
        NickName = nickName;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public String getNickName() {
        return NickName;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
