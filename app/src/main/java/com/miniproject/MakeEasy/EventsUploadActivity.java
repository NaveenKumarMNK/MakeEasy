package com.miniproject.MakeEasy;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class EventsUploadActivity extends AppCompatActivity {

    TextInputEditText event,clg,desc;
    private static final int PICK=1;
    MaterialButton next,ImageUpload;
    String EventName,College,description;
    Uri uri;
    StorageTask task;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseUser auth= FirebaseAuth.getInstance().getCurrentUser();
    String UploadUserId;
    ImageView previewImage;
    FirebaseStorage storage;
    String downloadUri,uploadDate;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    Timestamp uploadTIme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_upload);

        event=findViewById(R.id.EdittextEventName);
        desc=findViewById(R.id.EdittextDescription);
        clg=findViewById(R.id.EdittextCollege);
        ImageUpload=findViewById(R.id.ChooseButton);
        next=findViewById(R.id.NextButton);
        previewImage=findViewById(R.id.PreviewImage);

        UploadUserId=auth.getUid().toString();

        storage=FirebaseStorage.getInstance();

        ImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityResultLauncher.launch("image/*");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EventName=event.getText().toString();
               College=clg.getText().toString();
               description=desc.getText().toString();
               uploadTIme=Timestamp.now();
               calendar=Calendar.getInstance();
               dateFormat=new SimpleDateFormat("MM/dd/yyyy");
               uploadDate=dateFormat.format(calendar.getTime());

               if (task!=null && task.isInProgress())
               {
                   Toast.makeText(EventsUploadActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
               }else
               {
                   uploadImage();
               }



            }
        });

    }

    private void uploadImage() {
        if (uri!=null)
        {
            StorageReference storageReference=storage.getReference().child("Events/"+ UUID.randomUUID().toString()+"."+getFileExtension(uri));
          task=storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful())
                    {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Toast.makeText(EventsUploadActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
                                downloadUri=uri.toString();

                                EventsUploadInfo eventsUploadInfo=new EventsUploadInfo(EventName,downloadUri,College,UploadUserId,description,uploadDate,
                                        uploadTIme);

                                db.collection("Events").add(eventsUploadInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(EventsUploadActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                      //  Toast.makeText(EventsUploadActivity.this, "Image Upload successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //Toast.makeText(EventsUploadActivity.this, "Image Upload failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EventsUploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(EventsUploadActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    ActivityResultLauncher<String> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.GetContent()
            , new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result!=null)
                    {
                        previewImage.setImageURI(result);
                        uri=result;
                    } else
                    {
                        Toast.makeText(EventsUploadActivity.this, "null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}