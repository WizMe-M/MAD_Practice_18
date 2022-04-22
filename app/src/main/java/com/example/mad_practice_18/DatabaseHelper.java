package com.example.mad_practice_18;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "news_service.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql запрос в порядке
        String createDbSql = "create Table News(id int primary key, header, release_date, main_text, author); "
                + "create Table User(login primary key, password, is_admin default 0); "
                + "insert into User(login, password, is_admin) values ('admin', 'admin', 1), ('reader', 'reader', 0)";
        db.execSQL(createDbSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropAll(db);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAll(db);
        db.setVersion(oldVersion);
        onCreate(db);
    }

    private void dropAll(SQLiteDatabase db) {
        String dropAllSql = "drop Table if exists News; " +
                "drop Table if exists User";
        db.execSQL(dropAllSql);
    }

    public Boolean newsEmpty() {
        Cursor cursor = this.getNews();
        return cursor.getCount() == 0;
    }

    public Boolean addNews(String header, String datetime, String text, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("header", header);
        values.put("release_date", release_date);
        values.put("main_text", text);
        values.put("author", author);
        long result = db.insert("News", null, values);
        return result != -1;
    }

    public Boolean addNews(NewsModel news) {
        return addNews(news.Header, news.MainText, news.Date, news.Author);
    }

    public Cursor getNews() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from News";
        return db.rawQuery(sql, null);
    }

    public UserModel findUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select * from User";
        Cursor data = db.rawQuery(sql, null);
        while (data.moveToNext()) {
            String s1 = data.getString(0);
            String s2 = data.getString(1);
            if (login.equals(s1) && password.equals(s2)) {
                Boolean isAdmin = data.getInt(2) == 1;
                return new UserModel(s1, s2, isAdmin);
            }
        }
        data.close();
        return null;
    }

    public Boolean addReader(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", login);
        values.put("password", password);
        long result = db.insert("User", null, values);
        return result != -1;
    }

    public ArrayList<NewsModel> getAllNewsList() {
        ArrayList<NewsModel> results = new ArrayList<>();
        Cursor data = getNews();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String header = data.getString(1);
            String text = data.getString(2);
            String release_date = data.getString(3);
            String author = data.getString(4);

            NewsModel model = new NewsModel(id, header, release_date, text, author);
            results.add(model);
        }
        data.close();
        return results;
    }

    public Boolean updateNews(NewsModel news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("header", news.Header);
        cv.put("main_text", news.MainText);
        cv.put("release_date", news.Date);
        cv.put("author", news.Author);
        long result = db.update("News", cv, "_id = ?",
                new String[]{String.valueOf(news.Id)});
        return result != -1;
    }
}