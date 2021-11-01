package com.miniproject.MakeEasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

public class CreateRoomActivity extends AppCompatActivity {

    TextView codeView;
    TextInputEditText name,topic,department,subject;
    MaterialButton create,getClassCode;
    String Name,Topic,Dept,Subj;
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    String authId,DocId;
    Timestamp time;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    Random random=new Random();
    String randomInput="abcdefghijklmnopqrstuvwxy0z123456789";
    StringBuilder check=new StringBuilder();
    String singleString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        name=findViewById(R.id.EdittextName);
        topic=findViewById(R.id.EdittextTopic);
        department=findViewById(R.id.EdittextDepartment);
        subject=findViewById(R.id.EdittextSubject);
        getClassCode=findViewById(R.id.GetClassCode);
        create=findViewById(R.id.CreateClass);
        authId=user.getUid();

        db.collection("AdminInfo").whereEqualTo("id",authId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot q:queryDocumentSnapshots)
                {
                    DocId=q.getId();
                }
            }
        });

        getClassCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<6;i++)
                {
                    int index=(int)random.nextInt(randomInput.length());
                    char randomChar=randomInput.charAt(index);
                    check.append(randomChar);
                }
                singleString=check.toString();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name=name.getText().toString();
                Topic=topic.getText().toString();
                Dept=department.getText().toString();
                Subj=subject.getText().toString();
                time=Timestamp.now();

                ClassRoomDetails details=new ClassRoomDetails(Name,Topic,Dept,Subj,singleString,time);

                db.collection("AdminInfo").document(DocId).collection("ClassRoom")
                        .add(details).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(CreateRoomActivity.this, "ClassRoom created", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(CreateRoomActivity.this,AdminPanelActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

    }
}