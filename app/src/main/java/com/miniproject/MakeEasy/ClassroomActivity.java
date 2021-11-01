package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ClassroomActivity extends AppCompatActivity {

    ImageView classButton;
    final String[] choose={"Join Class","Create Class"};
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String id,name,Rollnumber;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);


        intent=getIntent();
        id=intent.getStringExtra("Id");

        db.collection("StudentInfo").document("Computer Technology")
                .collection("2019-2022").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q:queryDocumentSnapshots)
                {
                    StudentInfo studentInfo=q.toObject(StudentInfo.class);
                    if (studentInfo.getId().equals(id))
                    {
                        name=studentInfo.getName();
                        Rollnumber=studentInfo.getRollNumber();
                    }
                }
            }
        });

        classButton=findViewById(R.id.ClassButton);
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  new CreateClassDialog().show(getSupportFragmentManager(),"fragment");

                AlertDialog.Builder builder=new AlertDialog.Builder(ClassroomActivity.this);

                builder.setSingleChoiceItems(choose, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choose[which].equals("Join Class"))
                        {
                            Intent intent=new Intent(ClassroomActivity.this,JoinClassActivity.class);
                            intent.putExtra("Name",name);
                            intent.putExtra("RollNumber",Rollnumber);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(ClassroomActivity.this, "You have no access to create room", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ClassroomActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}