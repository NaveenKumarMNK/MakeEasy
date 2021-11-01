package com.miniproject.MakeEasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassRoomAdapter extends RecyclerView.Adapter<ClassRoomAdapter.MyViewHolder> {

    Context context;
    ArrayList<ClassRoomDetails> details;

    public ClassRoomAdapter(Context context, ArrayList<ClassRoomDetails> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ClassRoomDetails classRoomDetails= details.get(position);
        holder.heading.setText(classRoomDetails.getTopic());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView heading,name,subject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.Heading);
            name=itemView.findViewById(R.id.Name);
            subject=itemView.findViewById(R.id.SubjectName);
        }
    }
}
