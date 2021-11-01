package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText phone,Role;
    MaterialButton signUp;
    String phoneNumber,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phone=findViewById(R.id.EdittextPhone);
        signUp=findViewById(R.id.SignUpButton);
        Role=findViewById(R.id.EdittextRole);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber=phone.getText().toString();
                role=Role.getText().toString();
                if(!phoneNumber.isEmpty())
                {
                    if (phoneNumber.length()<=10)
                    {
                        if(role.equals("Student"))
                        {
                            Intent intent=new Intent(RegisterActivity.this,otpActivity.class);
                            intent.putExtra("Number",phoneNumber);
                            intent.putExtra("Role","Student");
                            startActivity(intent);
                        }
                        else if (role.equals("Teacher"))
                        {
                            Intent intent=new Intent(RegisterActivity.this,otpActivity.class);
                            intent.putExtra("Number",phoneNumber);
                            intent.putExtra("Role","Teacher");
                            startActivity(intent);
                        }

                        }
                    else{
                        Toast.makeText(RegisterActivity.this, "10 digits required", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Enter your number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}