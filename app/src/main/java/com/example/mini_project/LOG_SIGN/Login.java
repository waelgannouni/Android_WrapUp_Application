package com.example.mini_project.LOG_SIGN;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mini_project.Activities.HomeActivity;
import com.example.mini_project.Activities.LoadingScreen;
import com.example.mini_project.DbUtils.LocalDB;
import com.example.mini_project.DbUtils.User;
import com.example.mini_project.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public final String User_Pref = "User_Pref";

    EditText EmailInput,PasswordInput;
    TextInputLayout Email;
    Button LogIn;
    Button SignUp;
    Button ForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();
        InitEvent();
    }

    public void InitView(){
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //title
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        toolbar.setTitleMargin(450,0,0,0);
        getSupportActionBar().setTitle(getString(R.string.Login_Title));

        //menu
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        Email = (TextInputLayout) findViewById(R.id.Email);
        EmailInput = (EditText) findViewById(R.id.EmailInput);
        PasswordInput = (EditText) findViewById(R.id.PasswordInput);
        LogIn = (Button) findViewById(R.id.LogIn);
        SignUp = (Button) findViewById(R.id.SignUp);
        ForgotPassword = (Button) findViewById(R.id.ForgotPassword);
    }
    public void InitEvent(){
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=EmailInput.getText().toString();
                password=PasswordInput.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(Login.this, R.string.MissingEmail, Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(Login.this, R.string.MissingPassword, Toast.LENGTH_SHORT).show();
                }else if(password.length()<8){
                    Toast.makeText(Login.this, R.string.ShortPassword, Toast.LENGTH_SHORT).show();
                }else{
                    //loginDb
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                    LocalDB dbInstance = RoomImplementaion.getmInstance().getDbInstance();
                    User user = dbInstance.userDao().getUserByEmail(email);
                    if(user!=null && user.getEmail()!=null  && user.getEmail().equalsIgnoreCase(email) ){
                        if(user.getPassword()!=null   && user.getPassword().equalsIgnoreCase(password)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SharedPreferences credentials = getSharedPreferences(User_Pref, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = credentials.edit();
                                    editor.putString("NickName", user.getNickName());
                                    editor.putString("FirstName", user.getFirstname());
                                    editor.putString("LastName", user.getLastname());
                                    editor.putString("Email", user.getEmail());
                                    editor.putString("Password", user.getPassword());
                                    editor.commit();
                                    Toast.makeText(Login.this, "Welcome "+user.getNickName(), Toast.LENGTH_SHORT).show();
                                    Intent transition = new Intent(Login.this,
                                            HomeActivity.class);
                                    Login.this.startActivity(transition);
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Login.this, "Login failed wrong credantials", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Login failed (user not found)", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                        }
                    }).start();
                }

            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(Login.this,
                        Signup.class);
                Login.this.startActivity(transition);
            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useremail = EmailInput.getText().toString();
                User user=new User();
                if(Useremail.isEmpty() && !isEmailValid(Useremail)){
                    Toast.makeText(Login.this, "Type in your email", Toast.LENGTH_SHORT).show();
                }else{
                    Thread thread=  new Thread(new Runnable() {
                        @Override
                        public void run() {
                            LocalDB dbInstance = RoomImplementaion.getmInstance().getDbInstance();
                            User user = dbInstance.userDao().getUserByEmail(Useremail);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (user!=null && user.getEmail()!=null  && user.getEmail().equalsIgnoreCase(Useremail)){
                                        String to = user.getEmail();
                                        String subject="Login Credentials";
                                        String message="Email: "+user.getEmail()+"\n Password: "+user.getPassword();
                                        Intent email = new Intent(Intent.ACTION_SEND);
                                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                                        email.putExtra(Intent.EXTRA_TEXT, message);
                                        email.setType("message/rfc822");
                                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                                    }else{
                                        Toast.makeText(Login.this, "Email not found", Toast.LENGTH_SHORT).show();
                                    }
                                }});
                        }});
                    thread.start();
                }

            }
        });
        //helper
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
                    Email.setHelperText("valid email");
                }else{
                    Email.setHelperText("Unvalid email");
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