package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class JoinClassActivity extends AppCompatActivity {

    TextView name,roll;
    String Name,RollNo;
    Intent intent;
    ImageView back;
    TextInputEditText code;
    MaterialButton join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);


        name=findViewById(R.id.Name);
        roll=findViewById(R.id.Rollno);
        back=findViewById(R.id.back);
        code=findViewById(R.id.EdittextCode);
        join=findViewById(R.id.Join);

        intent=getIntent();
        Name=intent.getStringExtra("Name");
        RollNo=intent.getStringExtra("RollNumber");

        name.setText(Name);
        roll.setText(RollNo);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(JoinClassActivity.this,ClassroomActivity.class);
        startActivity(intent);
        finish();

    }
}