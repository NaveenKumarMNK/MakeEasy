package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminPanelActivity extends AppCompatActivity {

    RelativeLayout attendance,createClass,eventsUpload;
    FirebaseUser auth= FirebaseAuth.getInstance().getCurrentUser();
    String uid;
    CircleImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        attendance=findViewById(R.id.TakeAttendance);
        createClass=findViewById(R.id.CreateClass);
        eventsUpload=findViewById(R.id.UploadEvents);
        profile=findViewById(R.id.ProfileView);


//        uid=auth.getUid().toString();
        if (auth==null)
        {
            Intent intent=new Intent(AdminPanelActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanelActivity.this,AttendancePanelActivity.class);
                startActivity(intent);
                finish();
            }
        });

        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanelActivity.this,CreateClassActivity.class);
                startActivity(intent);
            }
        });

        eventsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanelActivity.this,EventsUploadActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanelActivity.this,ProfileViewActivity.class);
                startActivity(intent);
            }
        });
    }
}