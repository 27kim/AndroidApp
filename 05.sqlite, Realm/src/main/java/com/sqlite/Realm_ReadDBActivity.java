package com.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sqlite.util.DBHelper;
import com.sqlite.vo.MemoVo;

import io.realm.Realm;

/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교제에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
public class Realm_ReadDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        TextView titleView=(TextView)findViewById(R.id.read_title);
        TextView contentView=(TextView)findViewById(R.id.read_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm mRealm = Realm.getDefaultInstance();

        MemoVo vo = mRealm.where(MemoVo.class).equalTo("title", title).findFirst();

        titleView.setText(vo.title + " from Realm");
        contentView.setText(vo.content);
    }
}
