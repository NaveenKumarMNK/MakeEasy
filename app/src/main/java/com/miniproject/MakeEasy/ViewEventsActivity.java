package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class ViewEventsActivity extends AppCompatActivity {

    TextView description;
    ImageView event;
    Intent intent;
    String EventName,CollegeName,desc,org;
    TextView text,organized,organizedBy;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        event=findViewById(R.id.Events);
        description=findViewById(R.id.Description);
        text=findViewById(R.id.Text);
        organized=findViewById(R.id.TextOrganise);
        organizedBy=findViewById(R.id.OrganizedBy);

        intent=getIntent();

        EventName=intent.getStringExtra("EventName");
        CollegeName=intent.getStringExtra("CollegeName");
        uri=Uri.parse(intent.getStringExtra("ImageURL"));


        db.collection("Events").whereEqualTo("name",EventName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot q:queryDocumentSnapshots)
                {
                    EventsUploadInfo eventsUploadInfo=q.toObject(EventsUploadInfo.class);
                    desc= eventsUploadInfo.getDescription();
                    org=eventsUploadInfo.getCollege();


                    Picasso.with(ViewEventsActivity.this).load(uri).into(event);
                    organized.setText("Organized By :");
                    organizedBy.setText(org);
                    text.setText("Description :");
                    description.setText(desc);


                }
            }
        });


    }
}