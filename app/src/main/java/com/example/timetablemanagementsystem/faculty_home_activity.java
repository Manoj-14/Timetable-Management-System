package com.example.timetablemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class faculty_home_activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView ;

    facViewTtlFragment facViewTtlFragment = new facViewTtlFragment();
    FacViewProfFragment facViewProfFragment = new FacViewProfFragment();
    Fac_add_class_fragment fac_add_class_fragment = new Fac_add_class_fragment();
    TextView greetFac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);

        bottomNavigationView = findViewById(R.id.facBottomNavigationView);
//        greetFac = findViewById(R.id.greetFac);


        String name = getIntent().getStringExtra("name");
        String fid = getIntent().getStringExtra("fid");
        String branch = getIntent().getStringExtra("branch");
        String[] sem = getIntent().getStringArrayExtra("sem");
        int[] ttlSem = getIntent().getIntArrayExtra("ttlSem");
        String[] subCode = getIntent().getStringArrayExtra("subCode");
        String[] subName = getIntent().getStringArrayExtra("subCode");
        ArrayList<String> data = getIntent().getStringArrayListExtra("data");

//        Log.println(Log.DEBUG,"Data:", data.toString());


        Toast.makeText(this, Arrays.toString(ttlSem), Toast.LENGTH_SHORT).show();

//        greetFac.setText("Hii " +name);

        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("fid",fid);
        bundle.putString("branch",branch);
        bundle.putStringArray("sem",sem);
        bundle.putIntArray("ttlSem",getIntent().getIntArrayExtra("ttlSem"));
        bundle.putStringArray("subCode",getIntent().getStringArrayExtra("subCode"));
        bundle.putStringArray("subName",getIntent().getStringArrayExtra("subName"));
        bundle.putStringArray("mon",getIntent().getStringArrayExtra("mon"));
        bundle.putStringArray("tue",getIntent().getStringArrayExtra("tue"));
        bundle.putStringArray("wed",getIntent().getStringArrayExtra("wed"));
        bundle.putStringArray("thr",getIntent().getStringArrayExtra("thr"));
        bundle.putStringArray("fri",getIntent().getStringArrayExtra("fri"));
        bundle.putStringArray("sat",getIntent().getStringArrayExtra("sat"));
        replaceFragment(fac_add_class_fragment,bundle );

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.facAddCls:
                        replaceFragment(fac_add_class_fragment,bundle );
                        break;
                    case R.id.facTtl:
                        replaceFragment(facViewTtlFragment,bundle );
                        break;
                    case R.id.facProf:
                        replaceFragment(facViewProfFragment,bundle );
                }
                return true;
            }
        });



    }

    private void replaceFragment(Fragment fragment,Bundle bundle){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.facHomeFrame,fragment);
        fragment.setArguments(bundle);
        fragmentTransaction.commit() ;
    }

}