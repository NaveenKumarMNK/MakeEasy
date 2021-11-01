package com.miniproject.MakeEasy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.firestore.FirebaseFirestore;

public class CreateClassDialog extends DialogFragment {
    final String[] choose={"Join Class","Create Class"};
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

      builder.setSingleChoiceItems(choose, -1, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              Intent intent=new Intent(getActivity(),ClassroomActivity.class);
              startActivity(intent);
          }
      });
        return builder.create();
    }
}
