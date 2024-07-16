package com.example.project58.register;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project58.Main2Activity;
import com.example.project58.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    Context context;
    ArrayList<DRecords> DRecordsArraylist;

    public MyAdapter(Context context, ArrayList<DRecords> DRecordsArraylist) {

        this.context = context;
        this.DRecordsArraylist = DRecordsArraylist;
    }

    @NonNull
    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {

        DRecords dRecords = DRecordsArraylist.get(position);
        holder.name.setText(dRecords.getName());
        holder.sex.setText(dRecords.getSex());
        holder.group.setText(dRecords.getGroup());
        holder.cont.setText(dRecords.getContact());
        holder.address.setText(dRecords.getAddress());
        SetAnimation(holder.itemView, position);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",dRecords.getName());
                intent.putExtra("dob",dRecords.getDob());
                intent.putExtra("sex",dRecords.getSex());
                intent.putExtra("group",dRecords.getGroup());
                intent.putExtra("cont",dRecords.getContact());
                intent.putExtra("centre",dRecords.getCentre());
                intent.putExtra("date",dRecords.getDate());
                intent.putExtra("address",dRecords.getAddress());
                intent.putExtra("email",dRecords.getEmail());
                intent.putExtra("illness",dRecords.getIllness());
                intent.putExtra("medication",dRecords.getMedication());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DRecordsArraylist.size();
    }

    public void filterlist(ArrayList<DRecords> filteredlist) {
        DRecordsArraylist=filteredlist;
        notifyDataSetChanged();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,dob,sex,group,cont,centre,date,address,email,illness,medication;
        Button btn;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.input_fullName);
            btn = itemView.findViewById(R.id.iop);
            sex = itemView.findViewById(R.id.gender);
            group = itemView.findViewById(R.id.inputBloodGroup);
            cont = itemView.findViewById(R.id.inputMobile);
            address = itemView.findViewById(R.id.inputAddress);
        }
    }

    private void SetAnimation(View viewanimation, int position)
    {
        Animation slidein = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewanimation.startAnimation(slidein);
    }



}
