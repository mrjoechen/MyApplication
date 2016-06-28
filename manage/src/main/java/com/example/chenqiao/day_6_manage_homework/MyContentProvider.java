package com.example.chenqiao.day_6_manage_homework;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by CHENQIAO on 2016/3/2.
 */
public class MyContentProvider extends ContentProvider {
    SQLiteDatabase database;
    @Override
    public boolean onCreate() {
        MyDBHelper myDBHelper = new MyDBHelper(getContext(), "mydb.db", null, 1);
        database = myDBHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = database.query("person",projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
//
    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
