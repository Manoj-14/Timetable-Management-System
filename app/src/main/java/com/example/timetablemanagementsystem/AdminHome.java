package com.example.timetablemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.timetablemanagementsystem.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminHome extends AppCompatActivity {
   BottomNavigationView bottomNavigationView;

   StudentReg studentReg = new StudentReg();
   FacultyRegister facultyRegister = new FacultyRegister();
   TextView greetAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigationView = findViewById(R.id.adminBottomNavigationView);

//        getSupportFragmentManager().beginTransaction().replace(R.id.adminHomeFrame,studentReg);
        replaceFragment(studentReg);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminStuFrame:
                        replaceFragment(studentReg);
                        break;
                    case R.id.adminFacframe:
                        replaceFragment(facultyRegister);
                        break;
                }

                return true;
            }
        });

        greetAdmin = findViewById(R.id.greetAdmin);

        String adminName = getIntent().getStringExtra("name");
        String adminEmail = getIntent().getStringExtra("email");

        greetAdmin.setText("Hii "+adminName);

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminHomeFrame,fragment);
        fragmentTransaction.commit() ;
    }

    public void AdminHome(View v){
        greetAdmin = findViewById(R.id.greetAdmin);

        String adminName = getIntent().getStringExtra("name");
        String adminEmail = getIntent().getStringExtra("email");

        greetAdmin.setText("Hii "+adminName);
    }

}