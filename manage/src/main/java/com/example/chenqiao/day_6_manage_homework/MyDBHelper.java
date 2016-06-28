package com.example.chenqiao.day_6_manage_homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenqiao on 2016/2/27.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//  第一次建表时执行
        String sql="create table person (id integer primary key autoincrement,name varchar(20),age integer,gender char(1),phone varchar(20));";
        db.execSQL(sql);

    }

    @Override
//    更新数据库版本时执行
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
