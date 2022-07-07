package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fac_add_class_fragment extends Fragment {

    public static ArrayList<ModClass> dataList;
    Spinner mon ,tue,wed,thu,fri,sat,semDisp;
    Button addcls;
    EditText subCodeEdt , subNameEdt;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";

    public Fac_add_class_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activityObj = this.getActivity();

        dataList = new ArrayList<>();

        View myView = inflater.inflate(R.layout.fragment_fac_add_class_fragment, container, false);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        mon = (Spinner)myView.findViewById(R.id.mondayTime);
        tue = (Spinner)myView.findViewById(R.id.tuesdayTime);
        wed = (Spinner)myView.findViewById(R.id.wednesdayTime);
        thu = (Spinner)myView.findViewById(R.id.thrusdayTime);
        fri = (Spinner)myView.findViewById(R.id.fridayTime);
        sat = (Spinner)myView.findViewById(R.id.saturdayTime);
        semDisp = (Spinner)myView.findViewById(R.id.semList);

        subCodeEdt = (EditText)myView.findViewById(R.id.subcodeEdt);
        subNameEdt = (EditText)myView.findViewById(R.id.subnameEdt);



        String[] sem = getArguments().getStringArray("sem");
        String fid = getArguments().getString("fid");
        String branch = getArguments().getString("branch");


        ArrayAdapter<String> semAdapter = new ArrayAdapter<String>(activityObj,android.R.layout.simple_spinner_item,sem);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semDisp.setAdapter(semAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activityObj,
                R.array.timings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mon.setAdapter(adapter);
        tue.setAdapter(adapter);
        wed.setAdapter(adapter);
        thu.setAdapter(adapter);
        fri.setAdapter(adapter);
        sat.setAdapter(adapter);

        addcls = (Button)myView.findViewById(R.id.addcls);

        addcls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();

                map.put("fid",fid);
                map.put("branch",branch);
                map.put("subcode",subCodeEdt.getText().toString());
                map.put("subname",subNameEdt.getText().toString());
                map.put("sem",semDisp.getSelectedItem().toString());
                map.put("mon",mon.getSelectedItem().toString());
                map.put("tue",tue.getSelectedItem().toString());
                map.put("wed",wed.getSelectedItem().toString());
                map.put("thu",thu.getSelectedItem().toString());
                map.put("fri",fri.getSelectedItem().toString());
                map.put("sat",sat.getSelectedItem().toString());


                Call<Void> call = retrofitInterface.exefacAddCls(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(activityObj, "TimeTable added successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 201){
                            Toast.makeText(activityObj, "TimeTable updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 400){
                            Toast.makeText(activityObj, "Timetable exists", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 403){
                            Toast.makeText(activityObj, "Subject Time table already added by others", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activityObj, response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(activityObj, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

//                Toast.makeText(activityObj, semDisp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });



        // Inflate the layout for this fragment
        return myView;
    }
}