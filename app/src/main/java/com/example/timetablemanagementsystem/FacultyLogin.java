package com.example.timetablemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacultyLogin extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";

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
                            Toast.makeText(FacultyLogin.this, result.getSem()[1], Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(FacultyLogin.this , faculty_home_activity.class);

                            i.putExtra("name",result.getName());
                            i.putExtra("fid",result.getFid());
                            i.putExtra("branch",result.getBranch());

                            i.putExtra("sem",result.getSem());
                            i.putExtra("data",result.getData());

//                            Log.println(Log.DEBUG,"Data:",result.getData());

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