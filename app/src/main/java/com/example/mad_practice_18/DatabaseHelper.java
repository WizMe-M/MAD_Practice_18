package com.example.mad_practice_18;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create Table News(header primary key, datetime, text, author)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop Table if exists News";
        sqLiteDatabase.execSQL(sql);
    }

    public Boolean isEmpty(){
        Cursor cursor = this.getData();
        return cursor.getCount() == 0;
    }

    public Boolean add(String header, String datetime, String text, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("header", header);
        values.put("datetime", datetime);
        values.put("text", text);
        values.put("author", author);
        long result = db.insert("News", null, values);
        return result != -1;
    }

    public Boolean add(NewsModel news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("header", news.Header);
        values.put("datetime", news.Datetime);
        values.put("text", news.Text);
        values.put("author", news.Author);
        long result = db.insert("News", null, values);
        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from News";
        return db.rawQuery(sql, null);
    }
}