package com.example.timetablemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle_ttl_view extends RecyclerView.Adapter<Recycle_ttl_view.MyViewHolder> {

    Context context;
    ArrayList<ModClass> dataList;

    public Recycle_ttl_view(Context context, ArrayList<ModClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Recycle_ttl_view.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fac_time_table_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_ttl_view.MyViewHolder holder, int position) {
        ModClass modClass = dataList.get(position);
        holder.semData.setText(modClass.getSem());
        holder.subCodeData.setText(modClass.getSubCode());
        holder.subNameData.setText(modClass.getSubName());
        holder.monData.setText(modClass.getMon());
        holder.tueData.setText(modClass.getTue());
        holder.wedData.setText(modClass.getWed());
        holder.thuData.setText(modClass.getThu());
        holder.friData.setText(modClass.getFri());
        holder.satData.setText(modClass.getSat());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView semData , subCodeData , subNameData , monData , tueData , wedData, thuData, friData , satData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            semData = itemView.findViewById(R.id.listSem);
            subCodeData = itemView.findViewById(R.id.listSubC);
            subNameData = itemView.findViewById(R.id.listSubN);
            monData = itemView.findViewById(R.id.listMon);
            tueData  = itemView.findViewById(R.id.listTue);
            wedData  = itemView.findViewById(R.id.listWed);
            thuData  = itemView.findViewById(R.id.listThu);
            friData  = itemView.findViewById(R.id.listFri);
            satData  = itemView.findViewById(R.id.listSat);
        }
    }
}
