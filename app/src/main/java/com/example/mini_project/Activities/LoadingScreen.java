package com.example.mini_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.LOG_SIGN.Login;
import com.example.mini_project.R;

public class LoadingScreen extends AppCompatActivity {

    public final String User_Pref = "User_Pref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences credentials = getSharedPreferences(User_Pref, Context.MODE_PRIVATE);
                String NickName = credentials.getString("NickName", null);
                if(NickName==null || NickName.isEmpty()){

                Intent transition = new Intent(LoadingScreen.this,
                        Login.class);
                LoadingScreen.this.startActivity(transition);
                LoadingScreen.this.finish();
            }else{
                    Intent transition = new Intent(LoadingScreen.this,
                            HomeActivity.class);
                    LoadingScreen.this.startActivity(transition);
                    LoadingScreen.this.finish();
                }
            }

            ;
        }, 3000);
    }
}