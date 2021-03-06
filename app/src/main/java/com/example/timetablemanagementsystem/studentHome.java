package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.timetablemanagementsystem.databinding.ActivityMainBinding;

public class studentHome extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());


        String name = getIntent().getStringExtra("name");
        String usn = getIntent().getStringExtra("usn");
        String branch = getIntent().getStringExtra("branch");
        String sem = getIntent().getStringExtra("sem");

        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("usn",usn);
        bundle.putString("branch",branch);
        bundle.putString("sem",sem);
        bundle.putIntArray("ttlSem",getIntent().getIntArrayExtra("ttlSem"));
        bundle.putStringArray("subCode",getIntent().getStringArrayExtra("subCode"));
        bundle.putStringArray("subName",getIntent().getStringArrayExtra("subName"));
        bundle.putStringArray("mon",getIntent().getStringArrayExtra("mon"));
        bundle.putStringArray("tue",getIntent().getStringArrayExtra("tue"));
        bundle.putStringArray("wed",getIntent().getStringArrayExtra("wed"));
        bundle.putStringArray("thr",getIntent().getStringArrayExtra("thr"));
        bundle.putStringArray("fri",getIntent().getStringArrayExtra("fri"));
        bundle.putStringArray("sat",getIntent().getStringArrayExtra("sat"));
        bundle.putString("password",getIntent().getStringExtra("password"));

        replaceFragment(new HomeFragment(),bundle);


        binding.bottomNavigationView.setOnItemSelectedListener(Item -> {

            switch(Item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment(),bundle);
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment(),bundle);
                    break;
            }

            return true ;
        });
    }
    private void replaceFragment(Fragment fragment ,Bundle bundle){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragment.setArguments(bundle);
        fragmentTransaction.commit() ;
    }
}