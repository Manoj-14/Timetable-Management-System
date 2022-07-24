package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = global.BASE_URL;
    RecyclerView recyclerView;
    Recycle_ttl_view recycle_ttl_view;
    ArrayList<ModClass> dataList;
    ArrayList data;
    String usn,password;
    String[] subCode,subName,mon,tue,wed,thu,fri,sat,sem;
    int[] ttlSem;

    Button refreshStu;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView=inflater.inflate(R.layout.fragment_home, container, false);
        Activity activityObj = this.getActivity();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        recyclerView = (RecyclerView)myView.findViewById(R.id.stuRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityObj));
        recyclerView.setHasFixedSize(true);

        dataList = new ArrayList<ModClass>();

        recycle_ttl_view = new Recycle_ttl_view(activityObj,dataList);

        recyclerView.setAdapter(recycle_ttl_view);
        usn = getArguments().getString("usn");
        password = getArguments().getString("password");

        HashMap<String,String> myMap = new HashMap<>();
        myMap.put("usn",usn);
        myMap.put("password",password);
        Call<LoginRes> myCall = retrofitInterface.executeLogin(myMap);
        myCall.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                LoginRes myresult = response.body();

                ttlSem = myresult.getTtlSem();
                subCode = myresult.getSubCode();
                subName = myresult.getSubName();
                mon = myresult.getMon();
                tue = myresult.getTue();
                wed = myresult.getWed();
                thu = myresult.getThr();
                fri = myresult.getFri();
                sat = myresult.getSat();


                getData();
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Toast.makeText(activityObj, "Can't able to fetch", Toast.LENGTH_SHORT).show();
            }
        });

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
//
//        getData();

        refreshStu = (Button) myView.findViewById(R.id.refreshStu);
        refreshStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView = (RecyclerView)myView.findViewById(R.id.stuRecycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(activityObj));
                recyclerView.setHasFixedSize(true);

                dataList = new ArrayList<ModClass>();

                recycle_ttl_view = new Recycle_ttl_view(activityObj,dataList);

                recyclerView.setAdapter(recycle_ttl_view);
                HashMap<String,String> map = new HashMap<>();
                map.put("usn",usn);
                map.put("password",password);
                Call<LoginRes> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        LoginRes result = response.body();

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

                        Toast.makeText(activityObj, "Refreshed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
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
            dataList.add(modDataList);
        }

        recycle_ttl_view.notifyDataSetChanged();
    }
}