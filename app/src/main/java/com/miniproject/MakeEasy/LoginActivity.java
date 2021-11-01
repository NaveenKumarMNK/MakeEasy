package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.douyu.aes.core.AesUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    TextView createAccount;
    TextInputEditText roll,pass;
    String id,Password,Name;
    MaterialButton SignIn;
    String EncryptedPassword;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        roll=findViewById(R.id.EdittextRollno);
        pass=findViewById(R.id.EdittextPassword);
        SignIn=findViewById(R.id.SignInButton);
        createAccount=findViewById(R.id.NoAcc);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=roll.getText().toString();
                Password=pass.getText().toString();

                db.collection("AdminInfo").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot q:queryDocumentSnapshots)
                        {
                            AdminInfo adminInfo=q.toObject(AdminInfo.class);
                            if(adminInfo.getId().equals(id))
                            {
                                if (adminInfo.getPassword().equals(Password))
                                {
                                    Intent intent=new Intent(LoginActivity.this,AdminPanelActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Incorrect Id", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

    }
}