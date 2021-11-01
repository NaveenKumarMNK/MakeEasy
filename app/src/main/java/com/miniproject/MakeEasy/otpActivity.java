package com.miniproject.MakeEasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpActivity extends AppCompatActivity {

    String Verification;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks Callbacks;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    FirebaseAuth auth;
    Intent intent;
    String num,password,Role;
    PinView pinView;
    TextView otpTime;
    TextView resendCode;
    MaterialButton verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        pinView=findViewById(R.id.PinView);
        auth=FirebaseAuth.getInstance();
        verify=findViewById(R.id.VerifyButton);
        otpTime=findViewById(R.id.Time);
        resendCode=findViewById(R.id.ResendCode);


        intent=getIntent();
        num="+91"+intent.getStringExtra("Number");
       // password=intent.getStringExtra("Password");
        Role=intent.getStringExtra("Role");
        sendVerificationCode(num);

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(num);
                Toast.makeText(otpActivity.this, "OTP has been sent", Toast.LENGTH_SHORT).show();
            }
        });

        verify.setOnClickListener(v -> {

            String code=pinView.getText().toString();
            if (code.isEmpty())
            {
                Toast.makeText(otpActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            }
            else if(pinView.getItemCount() != 6){
                Toast.makeText(otpActivity.this, "Entered OTP is invalid", Toast.LENGTH_SHORT).show();
            }
            else {
                verifyCode(code);
            }
        });
    }
    private void sendVerificationCode(String number) {
        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(auth)
                .setActivity(otpActivity.this)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);

                        Verification=s;
                        resendingToken=forceResendingToken;
                    }
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        String code=phoneAuthCredential.getSmsCode();
                        if (code!=null)
                        {
                            pinView.setText(code);
                            verifyCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        Toast.makeText(otpActivity.this, e.getMessage()+number, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                        super.onCodeAutoRetrievalTimeOut(s);
                    }

                })
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
        startTimer(45 * 1000,1000);
    }

    private void startTimer(final long finish, long time)
    {
        CountDownTimer countDownTimer;
        countDownTimer=new CountDownTimer(finish,time) {
            @Override
            public void onTick(long millisUntilFinished) {
                long remindSec=millisUntilFinished/1000;
                otpTime.setText(remindSec/60+":"+ remindSec % 60);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(Verification,code);
        SignInByCredentials(credential);
    }

    private void SignInByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                boolean newuser = task.getResult().getAdditionalUserInfo().isNewUser();
                if(newuser)
                {
                    if (Role.equals("Student"))
                    {
                        Intent intent = new Intent(otpActivity.this, FillProfileActivity.class);
                        intent.putExtra("Number",num);
                        //   intent.putExtra("Password",password);
                        startActivity(intent);
                        finish();
                    }
                    else if (Role.equals("Teacher"))
                    {
                        Intent intent = new Intent(otpActivity.this, LoginActivity.class);
                        //   intent.putExtra("Password",password);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    if (Role.equals("Student"))
                    {
                        Intent intent = new Intent(otpActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (Role.equals("Teacher"))
                    {
                        Intent intent = new Intent(otpActivity.this, LoginActivity.class);
                        //   intent.putExtra("Password",password);
                        startActivity(intent);
                        finish();
                    }

                }
            }
            else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                Toast.makeText(otpActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(otpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}