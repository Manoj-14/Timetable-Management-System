package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StudentReg extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";

    Button regStudent;
    EditText stuNameEd,stuUsnEd , stuPassEd ;


    public StudentReg() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Activity activityObj = this.getActivity();
        View myView =inflater.inflate(R.layout.fragment_student_reg, container, false);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        regStudent =(Button) myView.findViewById(R.id.stuRegBtn);
        stuNameEd =(EditText) myView.findViewById(R.id.stuNameReg);
        stuUsnEd = (EditText)myView.findViewById(R.id.stuUsnReg);
        stuPassEd = (EditText)myView.findViewById(R.id.stuPasswordReg);
//        Toast.makeText(activityObj, "Hiii", Toast.LENGTH_SHORT).show();
        regStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();

                map.put("name",stuNameEd.getText().toString());
                map.put("usn",stuUsnEd.getText().toString());
                map.put("password",stuPassEd.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

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
        
        return myView;
    }


}