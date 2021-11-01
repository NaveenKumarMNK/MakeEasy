package com.miniproject.MakeEasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.rpc.Code;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AttendancePanelActivity extends AppCompatActivity {

    MaterialButton code;
    TextView CodeText;
    Random random=new Random();
    String randomInput="abcdefghijklmnopqrstuvwxy0z123456789";
    StringBuilder check=new StringBuilder();
    String singleString;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_panel);


        code=findViewById(R.id.GenerateCodeButton);
        CodeText=findViewById(R.id.TextCode);

        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeText.setText("");
                for(int i=0;i<6;i++)
                {
                    int index=(int)random.nextInt(randomInput.length());
                    char randomChar=randomInput.charAt(index);
                    check.append(randomChar);
                }
                singleString=check.toString();
                CodeText.setText(singleString);
               AttendancePojo attendancePojo=new AttendancePojo(singleString,"1");

                db.collection("AdminInfo").document("OPdVIm4UKHGbpsRG0Xo7").collection("Attendance")
                        .add(attendancePojo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(AttendancePanelActivity.this, "Code posted successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}