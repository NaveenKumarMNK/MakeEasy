package com.miniproject.MakeEasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewActivity extends AppCompatActivity {

    TextView signOut;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        signOut=findViewById(R.id.SignOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent=new Intent(ProfileViewActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}