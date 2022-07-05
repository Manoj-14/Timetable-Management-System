package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacultyRegister extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";
    Button facRegbtn;
    EditText facName , facBranch , facId;
    CheckBox sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;

    public FacultyRegister() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activityObj = this.getActivity();
        View myview =inflater.inflate(R.layout.fragment_faculty_register, container, false);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        facRegbtn = (Button) myview.findViewById(R.id.facRegBtn);
        facBranch = (EditText) myview.findViewById(R.id.facbranchEdt);
        facName = (EditText) myview.findViewById(R.id.facNameReg);
        facId = (EditText) myview.findViewById(R.id.facIdReg);

        sem1 = (CheckBox) myview.findViewById(R.id.Check1);
        sem2 = (CheckBox) myview.findViewById(R.id.Check2);
        sem3 = (CheckBox) myview.findViewById(R.id.Check3);
        sem4 = (CheckBox) myview.findViewById(R.id.Check4);
        sem5 = (CheckBox) myview.findViewById(R.id.Check5);
        sem6 = (CheckBox) myview.findViewById(R.id.Check6);
        sem7 = (CheckBox) myview.findViewById(R.id.Check7);
        sem8 = (CheckBox) myview.findViewById(R.id.Check8);

        facRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();

                map.put("name",facName.getText().toString());
                map.put("id",facId.getText().toString());
                map.put("branch",facBranch.getText().toString());
                if(sem1.isChecked()){
                    map.put("sem1",sem1.getText().toString());
                }
                if(sem2.isChecked()){
                    map.put("sem2",sem2.getText().toString());
                }
                if(sem3.isChecked()){
                    map.put("sem3",sem3.getText().toString());
                }
                if(sem4.isChecked()){
                    map.put("sem4",sem4.getText().toString());
                }
                if(sem5.isChecked()){
                    map.put("sem5",sem5.getText().toString());
                }
                if(sem6.isChecked()){
                    map.put("sem6",sem6.getText().toString());
                }
                if(sem7.isChecked()){
                    map.put("sem7",sem7.getText().toString());
                }
                if(sem8.isChecked()){
                    map.put("sem8",sem8.getText().toString());
                }

                Call<Void> call = retrofitInterface.exeFacReg(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(activityObj, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 400){
                            Toast.makeText(activityObj, "Already registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(activityObj, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        // Inflate the layout for this fragment
        return myview;
    }

}