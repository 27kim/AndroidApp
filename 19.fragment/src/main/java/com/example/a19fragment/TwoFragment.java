package com.example.a19fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class TwoFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("DialogFragment 제목");
        builder.setMessage("DialogFragment 내용");
        builder.setPositiveButton("확인",null);
        AlertDialog dialog = builder.create();
        //show 가 없네
        //dialog.show();
        return  dialog;
    }
}
