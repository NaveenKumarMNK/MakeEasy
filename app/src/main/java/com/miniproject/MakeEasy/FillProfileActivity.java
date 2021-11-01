package com.miniproject.MakeEasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.douyu.aes.core.AesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class FillProfileActivity extends AppCompatActivity {

    Intent intent;
    String number,password;
    TextInputEditText RollNumber,gender,Name,department,Batch;
    String roll,Gender,Department,name,batch;
    String encryptedPassword;
    Timestamp entryDate;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MaterialButton InButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profile);

        intent=getIntent();
        number=intent.getStringExtra("Number");
       // password=intent.getStringExtra("Password");

        RollNumber=findViewById(R.id.EdittextRollno);
        InButton=findViewById(R.id.InButton);
        Name=findViewById(R.id.EdittextName);
        gender=findViewById(R.id.EdittextGender);
        Batch=findViewById(R.id.EdittextBatch);
        department=findViewById(R.id.EdittextDepartment);
        uid=auth.getCurrentUser().getUid();



        InButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryDate= Timestamp.now();
                roll=RollNumber.getText().toString();
                name=Name.getText().toString();
                Gender=gender.getText().toString();
                batch=Batch.getText().toString();
                Department=department.getText().toString();

           //     encryptedPassword= AesUtils.INSTANCE.encryptECB(password);

                StudentInfo studentInfo=new StudentInfo(roll,name,Gender,Department,batch,uid,number,entryDate);

                if (Department.equals("CT"))
                {
                    db.collection("StudentInfo").document("Computer Technology")
                            .collection(batch).add(studentInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful())
                            {
                                Intent intent=new Intent(FillProfileActivity.this,HomeActivity.class);
                                intent.putExtra("Name",name);
                                startActivity(intent);
                                Toast.makeText(FillProfileActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(FillProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

}