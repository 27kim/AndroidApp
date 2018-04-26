package com.example.a12intent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    String[] projection = {
            "category",
            "location"
    };

    public DBHelper(Context context) {
        super(context, "mydb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL= "create table tb_data " +
                "(_id integer primary key autoincrement,"
                + "category,"
                + "location)";

        db.execSQL(memoSQL);

        db.execSQL("insert into tb_data (category, location) values ('0', '서울특별시')");
        db.execSQL("insert into tb_data (category, location) values ('0', '경기도')");

        db.execSQL("insert into tb_data (category, location) values ('서울특별시', '종로구')");
        db.execSQL("insert into tb_data (category, location) values ('서울특별시', '강남구')");
        db.execSQL("insert into tb_data (category, location) values ('서울특별시', '송파구')");

        db.execSQL("insert into tb_data (category, location) values ('경기도', '성남시')");
        db.execSQL("insert into tb_data (category, location) values ('경기도', '수원시')");
        db.execSQL("insert into tb_data (category, location) values ('경기도', '용인시')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion==DATABASE_VERSION){
            db.execSQL("drop table tb_data");
            onCreate(db);
        }

    }
    public ArrayList<String> query(Context context, String column, String whereClause) {
        ArrayList<String> returnData = new ArrayList<>();

        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor mainCursor = db.query("tb_data", projection, column + " = ?", new String[]{whereClause}, null, null, null);

        returnData = new ArrayList<>();

        while (mainCursor.moveToNext()){
            returnData.add(mainCursor.getString(1));
        }

        db.close();
        return returnData;
    }

}