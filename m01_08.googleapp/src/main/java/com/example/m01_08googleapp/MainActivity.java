package com.example.m01_08googleapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView callIcon;
    ImageView loactionIcon;
    ImageView internetIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callIcon = findViewById(R.id.mission2_call);
        loactionIcon = findViewById(R.id.mission2_location);
        internetIcon = findViewById(R.id.mission2_internet);

        callIcon.setOnClickListener(this);
        loactionIcon.setOnClickListener(this);
        internetIcon.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v ==callIcon){
            if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                startActivity(intent);
            }else{
                requestPermissions(new String [] {Manifest.permission.CALL_PHONE}, 100);
            }
        }else if(v ==loactionIcon){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952, 126.9779451"));
            startActivity(intent);
        }else if(v==internetIcon){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seoul.go.kr"));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==100 && grantResults.length >0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                startActivity(intent);
            }else{
                Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
