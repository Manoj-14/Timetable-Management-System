package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacultyLogin extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = global.BASE_URL;

    Button facLoginBtn;
    EditText facId,facPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        facLoginBtn = findViewById(R.id.facLoginBtn);
        facId = findViewById(R.id.facIdEdi);
        facPassword = findViewById(R.id.facPasswordEdt);

        facLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();

                map.put("fid",facId.getText().toString());
                map.put("password",facPassword.getText().toString());

                Call<FacLogin> call = retrofitInterface.exeFaclogin(map);

                call.enqueue(new Callback<FacLogin>() {
                    @Override
                    public void onResponse(Call<FacLogin> call, Response<FacLogin> response) {
                        if (response.code() == 200){

                            FacLogin result = response.body();

                            System.out.println(result);
//                            Log.println(Log.DEBUG,"Body",result.getFid());
                            Intent i = new Intent(FacultyLogin.this , faculty_home_activity.class);

                            i.putExtra("name",result.getName());
                            i.putExtra("fid",result.getFid());
                            i.putExtra("branch",result.getBranch());
                            i.putExtra("password",facPassword.getText().toString());
                            i.putExtra("sem",result.getSem());
                            i.putExtra("data", result.getData());
                            i.putExtra("ttlSem", result.getTtlSem());
                            i.putExtra("subCode", result.getSubCode());
                            i.putExtra("subName", result.getSubName());
                            i.putExtra("mon", result.getMon());
                            i.putExtra("tue", result.getTue());
                            i.putExtra("wed", result.getWed());
                            i.putExtra("thr", result.getThr());
                            i.putExtra("fri", result.getFri());
                            i.putExtra("sat", result.getSat());
//                            i.putExtra("ttl")
                            Toast.makeText(FacultyLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                            Log.println(Log.DEBUG,"Data:",result.getData().toString());
                            Log.println(Log.DEBUG,"Sem:",Arrays.toString(result.getTtlSem()).toString());
                            Log.println(Log.DEBUG,"SubCode:",Arrays.toString(result.getSubCode()).toString());
                            Log.println(Log.DEBUG,"Subname:",Arrays.toString(result.getSubName()).toString());
                            Log.println(Log.DEBUG,"Tue:",Arrays.toString(result.getMon()).toString());
                            Log.println(Log.DEBUG,"wed:",Arrays.toString(result.getTue()).toString());
                            Log.println(Log.DEBUG,"thr:",Arrays.toString(result.getWed()).toString());
                            Log.println(Log.DEBUG,"fri:",Arrays.toString(result.getThr()).toString());
                            Log.println(Log.DEBUG,"sat:",Arrays.toString(result.getFri()).toString());
                            Log.println(Log.DEBUG,"Mon:",Arrays.toString(result.getSat()).toString());
                            startActivity(i);
                        }

                        else if (response.code() == 404){
                            Toast.makeText(FacultyLogin.this, "Invalid password", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.code() == 400){
                            Toast.makeText(FacultyLogin.this, "User Not found", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<FacLogin> call, Throwable t) {
                        Toast.makeText(FacultyLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }
}