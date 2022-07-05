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

public class AdminLogin extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:9001";

    Button adminLoginbtn;
    EditText adminEmail,adminPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        adminLoginbtn = findViewById(R.id.adminLoginBtn);
        adminEmail = findViewById(R.id.adminEmail);
        adminPass = findViewById(R.id.adminPassword);
        
        adminLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              
//                Toast.makeText(AdminLogin.this, "Clicked", Toast.LENGTH_SHORT).show();
                HashMap<String,String> map = new HashMap<>();
                map.put("email",adminEmail.getText().toString());
                map.put("password",adminPass.getText().toString());
                Log.println(Log.DEBUG,"map",map.toString());
                Call<AdminRes>  call = retrofitInterface.exeAdminLogin(map);
                call.enqueue(new Callback<AdminRes>() {
                    @Override
                    public void onResponse(Call<AdminRes> call, Response<AdminRes> response) {

                        if(response.code() == 200){
                            AdminRes result = response.body();

                            Toast.makeText(AdminLogin.this, result.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent adHmintent = new Intent(AdminLogin.this,AdminHome.class);
                            adHmintent.putExtra("name",result.getName());
                            adHmintent.putExtra("email",result.getEmail());

                            startActivity(adHmintent);
                        }
                        else if(response.code() == 400){
                            Toast.makeText(AdminLogin.this,"User Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AdminRes> call, Throwable t) {
                        Toast.makeText(AdminLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}