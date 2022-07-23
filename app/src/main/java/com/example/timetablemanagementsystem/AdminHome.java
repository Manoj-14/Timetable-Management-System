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
   AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
   TextView greetAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigationView = findViewById(R.id.adminBottomNavigationView);
        greetAdmin = findViewById(R.id.greetAdmin);

        String adminName = getIntent().getStringExtra("name");
        String adminEmail = getIntent().getStringExtra("email");

        greetAdmin.setText("Hii "+adminName);

        Bundle bundle = new Bundle();
        bundle.putString("name",adminName);
        bundle.putString("email",adminEmail);
        replaceFragment(studentReg,bundle);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminStuFrame:
                        replaceFragment(studentReg,bundle);
                        break;
                    case R.id.adminFacframe:
                        replaceFragment(facultyRegister,bundle);
                        break;
                    case R.id.adminProfframe:
                        replaceFragment(adminProfileFragment,bundle);
                }

                return true;
            }
        });




    }
    private void replaceFragment(Fragment fragment,Bundle bundle){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminHomeFrame,fragment);
        fragment.setArguments(bundle);
        fragmentTransaction.commit() ;
    }



}