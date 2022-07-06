package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class facViewTtlFragment extends Fragment {

    RecyclerView recyclerView;
    Recycle_ttl_view recycle_ttl_view;
    ArrayList<ModClass> dataList;
    ArrayList data;
    String[] subCode,subName,mon,tue,wed,thu,fri,sat,sem;
    int[] ttlSem;


    public facViewTtlFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView =inflater.inflate(R.layout.fragment_fac_view_ttl, container, false);

        Activity activityObj = this.getActivity();


        recyclerView = (RecyclerView)myView.findViewById(R.id.facRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityObj));
        recyclerView.setHasFixedSize(true);
        
        dataList = new ArrayList<ModClass>();

        recycle_ttl_view = new Recycle_ttl_view(activityObj,dataList);

        recyclerView.setAdapter(recycle_ttl_view);



        sem = getArguments().getStringArray("sem");
        String fid = getArguments().getString("fid");
        String branch = getArguments().getString("branch");
        ArrayList data = getArguments().getStringArrayList("data");

        ttlSem = getArguments().getIntArray("ttlSem");
        subCode = getArguments().getStringArray("subCode");
        subName = getArguments().getStringArray("subName");
        mon = getArguments().getStringArray("mon");
        tue = getArguments().getStringArray("tue");
        wed = getArguments().getStringArray("wed");
        thu = getArguments().getStringArray("thr");
        fri = getArguments().getStringArray("fri");
        sat = getArguments().getStringArray("sat");

        getData();

        return myView;
    }

    private void getData() {

        for (int i=0;i< ttlSem.length ; i++){
            ModClass modDataList = new ModClass(ttlSem[i],subCode[i],subName[i], mon[i],tue[i],wed[i],thu[i],fri[i],sat[i] );
//            ModClass modDataList = new ModClass(1,"code","name", "mon","tue[i]","wed[i]","thu[i]","fri[i]","sat[i]" );
            dataList.add(modDataList);
        }

        recycle_ttl_view.notifyDataSetChanged();
    }
}