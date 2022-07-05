package com.example.timetablemanagementsystem;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<LoginRes> executeLogin(@Body HashMap<String,String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

    @POST("/adminLogin")
    Call<AdminRes> exeAdminLogin(@Body HashMap<String,String> map);

    @POST("/adminPassUpdate")
    Call<Void> exeAdminUpdate(@Body HashMap<String,String> map);

    @POST("/facReg")
    Call<Void> exeFacReg(@Body HashMap<String,String> map);
}
