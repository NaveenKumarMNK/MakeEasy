package com.miniproject.MakeEasy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UploadEventsAdapter extends RecyclerView.Adapter<UploadEventsAdapter.MyViewHolder> {
    ArrayList<EventsUploadInfo> events;
    Context context;

    public UploadEventsAdapter(ArrayList<EventsUploadInfo> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EventsUploadInfo eventsUploadInfo= events.get(position);
        Uri uri=Uri.parse(eventsUploadInfo.getUri());
        holder.CollegeName.setText(eventsUploadInfo.getCollege());
        holder.eventName.setText(eventsUploadInfo.getName());
        holder.eventImage.setImageURI(uri);
        Picasso.with(context).load(uri).into(holder.eventImage);
       holder.time.setText(eventsUploadInfo.getTime());
        holder.eventsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ViewEventsActivity.class);
                intent.putExtra("EventName",eventsUploadInfo.getName());
                intent.putExtra("CollegeName",eventsUploadInfo.getCollege());
                intent.putExtra("ImageURL",eventsUploadInfo.getUri());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView eventName,CollegeName,time;
        CardView eventsItem;
        ImageView eventImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.EventImage);
            eventsItem=itemView.findViewById(R.id.EventsItem);
             eventName=itemView.findViewById(R.id.EventName);
            CollegeName=itemView.findViewById(R.id.CollegeName);
            time=itemView.findViewById(R.id.Time);
        }
    }
}
