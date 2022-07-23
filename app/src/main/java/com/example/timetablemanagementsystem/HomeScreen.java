package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    Button b1 , skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();// This Line hides the action bar

        setContentView(R.layout.activity_home_screen);

        b1 = findViewById(R.id.asFac);
        skip = findViewById(R.id.asstu);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeScreen.this,homeScreen1.class);
                startActivity(i);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeScreen.this,SignUpOrIn.class);
                startActivity(i);
            }
        });
    }
}