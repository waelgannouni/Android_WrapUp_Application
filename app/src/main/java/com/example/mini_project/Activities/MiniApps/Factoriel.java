package com.example.mini_project.Activities.MiniApps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mini_project.R;

public class Factoriel extends AppCompatActivity {
    Button btnFact;
    TextView result;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoriel);
        initView();
        initEvent();
    }
    public Long FACT(int fact) {
        Long x = 1L;
        for (int i = 1; i <= fact; i++) {
            x = x * i;
        }
        return x;
    }
    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_Factoriel);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //title
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        toolbar.setTitleMargin(400,0,0,0);
        getSupportActionBar().setTitle(getString(R.string.Factoriel));

        //menu
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        btnFact = (Button) findViewById(R.id.calcule);
        result = (TextView) findViewById(R.id.result);
        input = (EditText) findViewById(R.id.Fact);
    }
    public void initEvent() {

        btnFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(Factoriel.this, getString(R.string.alert_1), Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(input.getText().toString()) < 0) {
                    Toast.makeText(Factoriel.this, getString(R.string.alert_2), Toast.LENGTH_LONG).show();
                } else {
                    Long fact = FACT(Integer.parseInt(input.getText().toString()));
                    result.setText("la factorielle de " + input.getText().toString() + " est : " + fact);
                }
            }
        });

    }

    }