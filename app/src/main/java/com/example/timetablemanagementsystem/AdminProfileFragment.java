package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminProfileFragment extends Fragment {

    TextView adminProfName;
    TextView adminProfEmail;
    Button adminPassUpt;
    EditText oldPassCng,newPassCng;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";


    public AdminProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activityObj = this.getActivity();
        View myView = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        adminProfName = (TextView)myView.findViewById(R.id.adminProfName);
        adminProfEmail = (TextView)myView.findViewById(R.id.adminProfEmail);
        adminPassUpt = (Button)myView.findViewById(R.id.adminProfUptBtn);

        String adminDbName = getArguments().getString("name");
        String adminDbEmail = getArguments().getString("email");

        adminProfName.setText(adminDbName);
        adminProfEmail.setText(adminDbEmail);



        adminPassUpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    //
                oldPassCng = (EditText)myView.findViewById(R.id.oldPassEdit);
                newPassCng = (EditText)myView.findViewById(R.id.newPassEdit);
                HashMap<String,String> map = new HashMap<>();
                map.put("name",adminDbName);
                map.put("email",adminDbEmail);
                map.put("oldPassword",oldPassCng.getText().toString());
                map.put("newPassword",newPassCng.getText().toString());



                Call<Void> call = retrofitInterface.exeAdminUpdate(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code() == 200){
                            Toast.makeText(activityObj, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(activityObj, "Please Enter the correct password", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 400){
                            Toast.makeText(activityObj, "Something happens wrong", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(activityObj, "Please Enter the values", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        return myView;

    }

    
}