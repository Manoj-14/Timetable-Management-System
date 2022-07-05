package com.example.timetablemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class faculty_home_activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView ;

    facViewTtlFragment facViewTtlFragment = new facViewTtlFragment();
    FacAddSylFragment facAddSylFragment = new FacAddSylFragment();
    FacViewProfFragment facViewProfFragment = new FacViewProfFragment();
    Fac_add_class_fragment fac_add_class_fragment = new Fac_add_class_fragment();
    TextView greetFac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);

        bottomNavigationView = findViewById(R.id.facBottomNavigationView);
        greetFac = findViewById(R.id.greetFac);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.facAddCls:
                        replaceFragment(fac_add_class_fragment);
                        break;
                    case R.id.facTtl:
                        replaceFragment(facViewTtlFragment);
                        break;
                    case R.id.facAddSyls:
                        replaceFragment(facAddSylFragment);
                        break;
                    case R.id.facProf:
                        replaceFragment(facViewProfFragment);
                }
                return true;
            }
        });



    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.facHomeFrame,fragment);
//        fragment.setArguments(bundle);
        fragmentTransaction.commit() ;
    }

}