package com.m01_3contact;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnAdd);
        txtEmail = findViewById(R.id.txtEmail);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(txtEmail.equals("")){
            Toast.makeText(this, "이메일 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtPhone.equals("")){
            Toast.makeText(this, "전화번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtName.equals("")){
            Toast.makeText(this, "이름이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ContactVO vo = realm.createObject(ContactVO.class);
                vo.email = txtEmail.getText().toString();
                vo.phone = txtPhone.getText().toString();
                vo.name = txtName.getText().toString();
            }
        });

        ContactVO data = mRealm.where(ContactVO.class).equalTo("name", txtName.getText().toString()).findFirst();

        Intent intent = new Intent(this, InsertResult.class);
        intent.putExtra("name", data.name);
        intent.putExtra("email", data.email);
        intent.putExtra("phone", data.phone);
        startActivity(intent);


    }
}
