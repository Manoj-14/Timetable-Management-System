package com.example.timetablemanagementsystem;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FacViewProfFragment extends Fragment {

    TextView facTxName , facTxFid , facTxBran;

    public FacViewProfFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_fac_view_prof, container, false);
        // Inflate the layout for this fragment
        Activity activityObj = this.getActivity();

        facTxName = (TextView)myView.findViewById(R.id.facProfName);
        facTxFid = (TextView)myView.findViewById(R.id.facProfFid);
        facTxBran = (TextView)myView.findViewById(R.id.facProfBran);

        String facDbName = getArguments().getString("name");
        String facDbFid = getArguments().getString("fid");
        String facDbBran = getArguments().getString("branch");

        facTxName.setText(facDbName);
        facTxFid.setText(facDbFid);
        facTxBran.setText(facDbBran);


        return myView;
    }
}