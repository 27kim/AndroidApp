package com.example.a08adapter_1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String [] arrayDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView arrayView = findViewById(R.id.main_list_array);
        arrayView.setOnItemClickListener(this);
        ListView simpleView = findViewById(R.id.main_list_simple);
        ListView cursorView = findViewById(R.id.main_list_cursor);

        arrayDatas = getResources().getStringArray(R.array.location);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrayDatas);
        arrayView.setAdapter(arrayAdapter);

        ArrayList<HashMap<String, String>> simpleDatas = new ArrayList<>();
        DBHelper helper =  new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_data", null);
        while (cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(1));
            map.put("content", cursor.getString(2));
            simpleDatas.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, simpleDatas, android.R.layout.simple_list_item_2,
                new String[]{"name","content"},
                new int[] {android.R.id.text1, android.R.id.text2}
                );

        simpleView.setAdapter(adapter);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,cursor,
                new String[]{"name","content"},
                new int[] {android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );

        cursorView.setAdapter(cursorAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, arrayDatas[position], Toast.LENGTH_SHORT).show();
    }
}
