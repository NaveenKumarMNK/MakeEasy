package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout attendance,joinClass,Events;
    Intent intent;
    String name;
    TextView UserName;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CircleImageView profileView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(user==null)
        {
            Intent intent=new Intent(HomeActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
        else
        {
            id=user.getUid();
        }

        attendance=findViewById(R.id.Attendance);
        profileView=findViewById(R.id.ProfileView);
        joinClass=findViewById(R.id.JoinClass);
        Events=findViewById(R.id.Events);
        UserName=findViewById(R.id.UserName);

        intent=getIntent();
        name=intent.getStringExtra("Name");
        UserName.setText(name);

        db.collection("StudentInfo").document("Computer Technology")
                .collection("2019-2022").whereEqualTo("id",id).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot q:queryDocumentSnapshots)
                        {
                            StudentInfo studentInfo=q.toObject(StudentInfo.class);
                            UserName.setText(studentInfo.getName());
                        }
                    }
                });

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,ProfileViewActivity.class);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,AttendanceActivity.class);
                startActivity(intent);
            }
        });
        joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,ClassroomActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,EventsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}