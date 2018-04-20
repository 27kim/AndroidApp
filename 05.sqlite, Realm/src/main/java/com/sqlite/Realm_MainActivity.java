package com.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sqlite.util.DBHelper;
import com.sqlite.vo.MemoVo;

import io.realm.Realm;

public class Realm_MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText titleView;
    EditText contentView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleView=(EditText)findViewById(R.id.add_title);
        contentView=(EditText)findViewById(R.id.add_content);
        addBtn=(Button)findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String title=titleView.getText().toString();
        final String content=contentView.getText().toString();

        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoVo vo = realm.createObject(MemoVo.class);
                vo.title = title;
                vo.content = content;
            }
        });

        Intent intent=new Intent(this, Realm_ReadDBActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
