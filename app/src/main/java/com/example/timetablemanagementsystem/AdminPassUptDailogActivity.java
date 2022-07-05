package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPassUptDailogActivity extends AppCompatActivity {
    Button uptpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_pass_upt_dailog);
    
        uptpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminPassUptDailogActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }
}