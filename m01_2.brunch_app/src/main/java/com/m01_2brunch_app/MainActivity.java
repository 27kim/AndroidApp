package com.m01_2brunch_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("종료 하시겠습니까?");
        dialog.setPositiveButton("OK",dialogListener);
        dialog.setNegativeButton("NO",null);
        AlertDialog ad = dialog.create();
        ad.show();
    }
}
