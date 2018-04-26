package com.example.a12intent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView detail;
    DBHelper helper;
    String source;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        helper = new DBHelper(this);
        detail = findViewById(R.id.detail_list);
        detail.setOnItemClickListener(this);

        data = new ArrayList<>();
        Intent intent = getIntent();
        source= getIntent().getStringExtra("data");

        data = helper.query(this,"category" ,intent.getStringExtra("data"));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1 , helper.query(this,"category" ,intent.getStringExtra("data")));

        detail.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("result", source + " " + data.get(position) );
        setResult(RESULT_OK, intent);
        finish();
    }
}
