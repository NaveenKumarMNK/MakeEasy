package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ArrayList<EventsUploadInfo> eventsUploadInfos=new ArrayList<>();
    RecyclerView eventsRecyclerView;
    UploadEventsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsRecyclerView=findViewById(R.id.EventsList);


        db.collection("Events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q:queryDocumentSnapshots)
                {
                    EventsUploadInfo events=q.toObject(EventsUploadInfo.class);
                    eventsUploadInfos.add(events);

                }
                adapter=new UploadEventsAdapter(eventsUploadInfos,EventsActivity.this);
                eventsRecyclerView.setHasFixedSize(true);
                eventsRecyclerView.setLayoutManager(new LinearLayoutManager(EventsActivity.this));
                eventsRecyclerView.setAdapter(adapter);
            }
        });
    }
}