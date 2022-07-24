package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class facViewTtlFragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = global.BASE_URL;

    RecyclerView recyclerView;
    Recycle_ttl_view recycle_ttl_view;
    ArrayList<ModClass> dataList;
    ArrayList data;
    String[] subCode,subName,mon,tue,wed,thu,fri,sat,sem;
    int[] ttlSem;
    Button refreshBtn;


    public facViewTtlFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView =inflater.inflate(R.layout.fragment_fac_view_ttl, container, false);

        Activity activityObj = this.getActivity();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        recyclerView = (RecyclerView)myView.findViewById(R.id.facRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityObj));
        recyclerView.setHasFixedSize(true);
        
        dataList = new ArrayList<ModClass>();

        recycle_ttl_view = new Recycle_ttl_view(activityObj,dataList);

        recyclerView.setAdapter(recycle_ttl_view);


        String fid = getArguments().getString("fid");
        HashMap<String,String> myMap = new HashMap<>();


        myMap.put("fid",fid);
        myMap.put("password",getArguments().getString("password"));

        Call<FacLogin> myCall = retrofitInterface.exeFaclogin(myMap);

        myCall.enqueue(new Callback<FacLogin>() {
            @Override
            public void onResponse(Call<FacLogin> call, Response<FacLogin> response) {
                FacLogin myResult = response.body();

                ttlSem = myResult.getTtlSem();
                subCode = myResult.getSubCode();
                subName = myResult.getSubName();
                mon = myResult.getMon();
                tue = myResult.getTue();
                wed = myResult.getWed();
                thu = myResult.getThr();
                fri = myResult.getFri();
                sat = myResult.getSat();

                getData();
            }

            @Override
            public void onFailure(Call<FacLogin> call, Throwable t) {
                Toast.makeText(activityObj, "Can't able to refresh", Toast.LENGTH_SHORT).show();
            }
        });


//
//        sem = getArguments().getStringArray("sem");
//
//        String branch = getArguments().getString("branch");
//        ArrayList data = getArguments().getStringArrayList("data");
//
//        ttlSem = getArguments().getIntArray("ttlSem");
//        subCode = getArguments().getStringArray("subCode");
//        subName = getArguments().getStringArray("subName");
//        mon = getArguments().getStringArray("mon");
//        tue = getArguments().getStringArray("tue");
//        wed = getArguments().getStringArray("wed");
//        thu = getArguments().getStringArray("thr");
//        fri = getArguments().getStringArray("fri");
//        sat = getArguments().getStringArray("sat");
//
//        getData();

        refreshBtn = (Button) myView.findViewById(R.id.refreshFac);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView = (RecyclerView)myView.findViewById(R.id.facRecycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(activityObj));
                recyclerView.setHasFixedSize(true);

                dataList = new ArrayList<ModClass>();

                recycle_ttl_view = new Recycle_ttl_view(activityObj,dataList);

                recyclerView.setAdapter(recycle_ttl_view);

                HashMap<String,String> map = new HashMap<>();

                map.put("fid",fid);
                map.put("password",getArguments().getString("password"));

                Call<FacLogin> call = retrofitInterface.exeFaclogin(map);

                call.enqueue(new Callback<FacLogin>() {
                    @Override
                    public void onResponse(Call<FacLogin> call, Response<FacLogin> response) {
                        FacLogin result = response.body();

                        ttlSem = result.getTtlSem();
                        subCode = result.getSubCode();
                        subName = result.getSubName();
                        mon = result.getMon();
                        tue = result.getTue();
                        wed = result.getWed();
                        thu = result.getThr();
                        fri = result.getFri();
                        sat = result.getSat();

                        getData();

                        Log.println(Log.DEBUG,"subCode", Arrays.toString(subCode));

                    }

                    @Override
                    public void onFailure(Call<FacLogin> call, Throwable t) {
                        Toast.makeText(activityObj, "Can't able to refresh", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

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