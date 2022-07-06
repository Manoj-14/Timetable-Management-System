package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentLogin extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";
    Button login;
    EditText usnEdit,passwordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        login = findViewById(R.id.stuLoginBtn);
        usnEdit = findViewById(R.id.stuUsn);
        passwordEdit = findViewById(R.id.stuPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();
                map.put("usn",usnEdit.getText().toString());
                map.put("password",passwordEdit.getText().toString());
                Call<LoginRes> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        if(response.code()==200){

                            LoginRes result = response.body();

                            Toast.makeText(StudentLogin.this,result.getSem(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(StudentLogin.this,studentHome.class);
                            i.putExtra("name",result.getName());
                            i.putExtra("usn" ,result.getUsn());
                            i.putExtra("branch",result.getBranch());
                            i.putExtra("sem",result.getSem());

                            i.putExtra("ttlSem", result.getTtlSem());
                            i.putExtra("subCode", result.getSubCode());
                            i.putExtra("subName", result.getSubName());
                            i.putExtra("mon", result.getMon());
                            i.putExtra("tue", result.getTue());
                            i.putExtra("wed", result.getWed());
                            i.putExtra("thr", result.getThr());
                            i.putExtra("fri", result.getFri());
                            i.putExtra("sat", result.getSat());

                            Log.println(Log.DEBUG,"Sem:", Arrays.toString(result.getTtlSem()).toString());
                            Log.println(Log.DEBUG,"SubCode:",Arrays.toString(result.getSubCode()).toString());
                            Log.println(Log.DEBUG,"Subname:",Arrays.toString(result.getSubName()).toString());
                            Log.println(Log.DEBUG,"Tue:",Arrays.toString(result.getMon()).toString());
                            Log.println(Log.DEBUG,"wed:",Arrays.toString(result.getTue()).toString());
                            Log.println(Log.DEBUG,"thr:",Arrays.toString(result.getWed()).toString());
                            Log.println(Log.DEBUG,"fri:",Arrays.toString(result.getThr()).toString());
                            Log.println(Log.DEBUG,"sat:",Arrays.toString(result.getFri()).toString());
                            Log.println(Log.DEBUG,"Mon:",Arrays.toString(result.getSat()).toString());

                            startActivity(i);

                        }else if(response.code()==404){
                            Toast.makeText(StudentLogin.this, "Wrong cresidential", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code()==400){
                            Toast.makeText(StudentLogin.this, "Not Registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Toast.makeText(StudentLogin.this, t.getMessage()   , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}