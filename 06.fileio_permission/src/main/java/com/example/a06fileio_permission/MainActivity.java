package com.example.a06fileio_permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    boolean fileReadPermission;
    boolean fileWritePermission;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.content);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);

        //Permission을 확인한다
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            fileReadPermission = true;
        }

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            fileWritePermission = true;
        }

        //Permission이 없다면 Permission을 요청한다
        if(!fileReadPermission|| !fileWritePermission){
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
        }
    }

    @Override
    //Permission 부여 요청 결과 확인
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==200 && grantResults.length>0){
            if(grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                fileWritePermission = true;
            if(grantResults[1] ==PackageManager.PERMISSION_GRANTED)
                fileWritePermission = true;
        }
    }

    @Override
    public void onClick(View v) {
        String content = contentView.getText().toString();
        if(fileWritePermission&& fileWritePermission){
            FileWriter writer;
            try{
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp";
                File dir = new File(dirPath);
                if(!dir.exists()){
                    dir.mkdir();
                }
                File file = new File(dir+"/myfile.txt");
                writer = new FileWriter(file,true);
                writer.write(content);
                writer.flush();
                writer.close();

                Intent intent =  new Intent(this, ReadFileActivity.class);
                startActivity(intent);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Pemission이 없어서 수행이 불가 합니다.", Toast.LENGTH_LONG).show();
        }
    }
}
