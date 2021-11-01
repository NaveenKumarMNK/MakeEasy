package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateClassActivity extends AppCompatActivity {

    ImageView createClass;
    final String[] choose={"Join Class","Create Class"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        createClass=findViewById(R.id.CreateClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CreateClassActivity.this);

                builder.setSingleChoiceItems(choose, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choose[which].equals("Create Class"))
                        {
                            Intent intent=new Intent(CreateClassActivity.this,CreateRoomActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(CreateClassActivity.this, "You have no access to join room", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}