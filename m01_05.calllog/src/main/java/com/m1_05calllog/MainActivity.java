package com.m1_05calllog;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE},200);
        }


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();
        Cursor cursor = db.query("tb_calllog", null, null, null, null, null, null );

        ArrayList<CallVO> data = new ArrayList<>();
        while (cursor.moveToNext()){
            CallVO raw = new CallVO();
            raw.name = cursor.getString(1);
            raw.date = cursor.getString(3);
            raw.icon1 = cursor.getString(2);
            //raw.icon2 = cursor.getColumnName(4);
            raw.phone = cursor.getColumnName(4);

            data.add(raw);
        }
        CallAdapter adapter = new CallAdapter(this, R.layout.main_list_item, data);
        listView.setAdapter(adapter);

    }
    @Override
    //Permission 부여 요청 결과 확인
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==200 && grantResults.length>0){
//            if(grantResults[0] ==PackageManager.PERMISSION_GRANTED)
////                fileWritePermission = true;
//            if(grantResults[1] ==PackageManager.PERMISSION_GRANTED)
//                fileWritePermission = true;
        }
    }
}
