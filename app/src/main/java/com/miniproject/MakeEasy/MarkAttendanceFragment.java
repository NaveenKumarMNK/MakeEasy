package com.miniproject.MakeEasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MarkAttendanceFragment extends Fragment {

    TextInputEditText otp;
    TextView Time;
    String OtpText;
    MaterialButton verify;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseUser auth=FirebaseAuth.getInstance().getCurrentUser();
    String authId;
    String DocId,DocIdTwo;
    ArrayList<String> markAtt=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_mark_attendance, container, false);
        Time=v.findViewById(R.id.time);
        otp=v.findViewById(R.id.EdittextOTP);
        verify=v.findViewById(R.id.VerifyButton);

        authId=auth.getUid();
        db.collection("StudentInfo").document("Computer Technology")
                .collection("2019-2022").whereEqualTo("id",authId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot q:queryDocumentSnapshots)
                {
                    DocId=q.getId();
                    Log.e("Id",DocId);
                }
            }
        });

        db.collection("AdminInfo").document("OPdVIm4UKHGbpsRG0Xo7").collection("Attendance")
                .whereEqualTo("received","1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty())
                {
                    for (QueryDocumentSnapshot q:queryDocumentSnapshots)
                    {
                        DocIdTwo=q.getId();
                    }
                }

            }
        });

       verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtpText=otp.getText().toString();

                if (OtpText.length()!=0)
                {
                    db.collection("AdminInfo").document("OPdVIm4UKHGbpsRG0Xo7").collection("Attendance")
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty())
                            {
                                for (QueryDocumentSnapshot q:queryDocumentSnapshots)
                                {
                                    AttendancePojo attendancePojo=q.toObject(AttendancePojo.class);

                                    if (attendancePojo.getAttendance().equals(OtpText))
                                    {
                                        Calendar c=Calendar.getInstance();
                                        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String t=s.format(c.getTime());
                                        markAtt.add("Yes");
                                        markAtt.add(t);
                                        PostedAttendance postedAttendance=new PostedAttendance(markAtt);
                                        db.collection("StudentInfo").document("Computer Technology")
                                                .collection("2019-2022").document(DocId).collection("Attendance")
                                                .add(postedAttendance).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(getActivity(), "Attendance posted successfully", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(getActivity(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(), "Enter the correct code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Code Not Available", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(getActivity(), "field empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startTimer(45 * 1000,1000);
        return v;
    }
    private void startTimer(final long finish, long time)
    {
        CountDownTimer countDownTimer;
        countDownTimer=new CountDownTimer(finish,time) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                long remindSec=millisUntilFinished/1000;
                Time.setText(remindSec/60+":"+ remindSec % 60);
            }

            @Override
            public void onFinish() {
                db.collection("AdminInfo").document("OPdVIm4UKHGbpsRG0Xo7").collection("Attendance")
                        .document(DocIdTwo).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getActivity(), "Code Expired", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }.start();
    }
}