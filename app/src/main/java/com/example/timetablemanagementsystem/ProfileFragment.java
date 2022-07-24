package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class ProfileFragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = global.BASE_URL;

    TextView stuName,stuUsn,stuBran,stuSem;
    Button stuPasUpt;
    EditText stuOld,stuNew;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_profile, container, false);

        Activity activityObj = this.getActivity();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        String stuDbName = getArguments().getString("name");
        String stuDbUsn = getArguments().getString("usn");
        String stuDbBran = getArguments().getString("branch");
        String stuDbSem = getArguments().getString("sem");

        stuPasUpt = (Button)myView.findViewById(R.id.uptStuPass);
        stuName = (TextView)myView.findViewById(R.id.stuNameEdt);
        stuUsn = (TextView)myView.findViewById(R.id.stuUsnEdt);
        stuBran = (TextView)myView.findViewById(R.id.stubranEdt);
        stuSem= (TextView)myView.findViewById(R.id.stuSemEdt);

        stuName.setText(stuDbName);
        stuUsn.setText(stuDbUsn);
        stuBran.setText(stuDbBran);
        stuSem.setText(stuDbSem);

        stuPasUpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stuOld = (EditText)myView.findViewById(R.id.stuOldPass);
                stuNew = (EditText)myView.findViewById(R.id.stuNewPass);

                HashMap<String,String> map = new HashMap<>();
                map.put("usn",stuDbUsn);
                map.put("oldPassword",stuOld.getText().toString());
                map.put("newPassword",stuNew.getText().toString());

                Call<Void> call = retrofitInterface.exeStuPassUpt(map);
                
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){
                            Toast.makeText(activityObj, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 400){
                            Toast.makeText(activityObj, "Incorrect old password", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(activityObj, "Something Went wrong", Toast.LENGTH_SHORT).show();
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