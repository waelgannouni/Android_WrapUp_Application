package com.example.mini_project.LOG_SIGN;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mini_project.Activities.HomeActivity;
import com.example.mini_project.DbUtils.LocalDB;
import com.example.mini_project.DbUtils.User;
import com.example.mini_project.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {

    public final String User_Pref = "User_Pref";


    EditText NickNameInput,FirstNameInput,LastNameInput,EmailInput,PasswordInput,ConfirmPasswordInput;
    TextInputLayout NickName,FirstName,LastName,Email,Password,ConfirmPassword;
    Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        InitView();
        InitEvent();
    }
    public void InitView(){
            Toolbar toolbar = findViewById(R.id.toolbar_signup);
            setSupportActionBar(toolbar);
            //flesh
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //title
            toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
            toolbar.setTitleMargin(300,0,0,0);
            getSupportActionBar().setTitle(getString(R.string.Signup_Title));

            //menu
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            //
            NickName = (TextInputLayout) findViewById(R.id.NickName);
            FirstName = (TextInputLayout) findViewById(R.id.FirstName);
            LastName = (TextInputLayout) findViewById(R.id.LastName);
            Email = (TextInputLayout) findViewById(R.id.Email);
            Password = (TextInputLayout) findViewById(R.id.Password);
            ConfirmPassword = (TextInputLayout) findViewById(R.id.ConfirmPassword);

            NickNameInput = (EditText) findViewById(R.id.NickNameInput);
            FirstNameInput = (EditText) findViewById(R.id.FirstNameInput);
            LastNameInput = (EditText) findViewById(R.id.LastNameInput);
            EmailInput = (EditText) findViewById(R.id.EmailInput);
            PasswordInput = (EditText) findViewById(R.id.PasswordInput);
            ConfirmPasswordInput = (EditText) findViewById(R.id.ConfirmPasswordInput);
            SignUp = (Button) findViewById(R.id.SignUp);
        }

    public void InitEvent(){
        EmailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Email.setHelperText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(isEmailValid(EmailInput.getText().toString())){
                    Email.setHelperText("");
                }else{
                    Email.setHelperText("Unvalid email");
                }

            }
        });
        ConfirmPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ConfirmPassword.setHelperText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String confirmpasswordinput=ConfirmPasswordInput.getText().toString();
                String passwordinput=PasswordInput.getText().toString();
                if(confirmpasswordinput.equals(passwordinput)){
                    ConfirmPassword.setHelperText("Passwords match");
                }else{
                    ConfirmPassword.setHelperText("Passwords doesn't match");
                }

            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkNM=false,checkFN=false,checkLN=false,checkE=false,checkP=false,checkCP=false;
                if(NickNameInput.getText().toString().isEmpty()){
                    NickName.setHelperText("Please Type in your NickName!");
                    checkNM=false;
                }else {
                    NickName.setHelperText("");
                    checkNM=true;
                }

                if(FirstNameInput.getText().toString().isEmpty()){
                    checkFN=false;
                    FirstName.setHelperText("Please Type in your FirstName!");
                }else{
                    FirstName.setHelperText("");
                    checkFN=true;
                }
                if(LastNameInput.getText().toString().isEmpty()){
                    checkLN=false;
                    LastName.setHelperText("Please Type in your LastName!");
                }else {
                    LastName.setHelperText("");
                    checkLN=true;
                }
                if(EmailInput.getText().toString().isEmpty() && !isEmailValid(EmailInput.getText().toString())){
                    checkE=false;
                    Email.setHelperText("Please Type in your Email!");
                }else{
                    checkE=true;
                    Email.setHelperText("");
                }
                if(PasswordInput.getText().toString().isEmpty()){
                    checkP=false;
                    Password.setHelperText("Please Type in your Password!");
                }else{
                    checkP=true;
                    Password.setHelperText("");
                }
                if((ConfirmPasswordInput.getText().toString().isEmpty()) && (ConfirmPasswordInput.getText().toString() != PasswordInput.getText().toString() )){
                    checkCP=false;
                    ConfirmPassword.setHelperText("Please confirm your Password!");
                }else {
                    checkCP=true;
                    ConfirmPassword.setHelperText("");
                }
                if((checkNM==true)&&(checkFN==true)&(checkLN==true)&(checkE==true)&&(checkP==true)&&(checkCP==true)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String nickname=NickNameInput.getText().toString();
                            String firstname=FirstNameInput.getText().toString();
                            String lastname=LastNameInput.getText().toString();
                            String email=EmailInput.getText().toString();
                            String password=PasswordInput.getText().toString();
                            User user = new User();
                            user.setNickName(nickname);
                            user.setFirstname(firstname);
                            user.setLastname(lastname);
                            user.setEmail(email);
                            user.setPassword(password);
                            LocalDB dbInstance = RoomImplementaion.getmInstance().getDbInstance();
                            dbInstance.userDao().createUser(user);
                            SharedPreferences credentials = getSharedPreferences(User_Pref, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = credentials.edit();
                            editor.putString("NickName", nickname);
                            editor.putString("FirstName", firstname);
                            editor.putString("LastName", lastname);
                            editor.putString("Email", email);
                            editor.putString("Password", password);
                            editor.commit();
                        }
                    }).start();
                    Signup.this.finish();
                    SharedPreferences credentials = getSharedPreferences(User_Pref, Context.MODE_PRIVATE);
                    Intent transition = new Intent(Signup.this,
                            HomeActivity.class);
                    Signup.this.startActivity(transition);
                    Toast.makeText(Signup.this, "Welcome "+credentials.getString("NickName", null), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}