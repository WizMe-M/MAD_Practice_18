package com.example.mad_practice_18;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminNewsActivity extends AppCompatActivity {
DatabaseHelper database;
    Button addNews;
    RecyclerView newsRecycler;

    NewsModel news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);

        database = new DatabaseHelper(this);
        ArrayList<NewsModel> newsList = new ArrayList<>();
        Cursor data = database.getNews();
        while (data.moveToNext()){
            String header = data.getString(0);
            String text = data.getString(1);
            String datetime = data.getString(2);
            String author = data.getString(3);

            NewsModel model = new NewsModel(header, datetime, text, author);
            newsList.add(model);
        }
        data.close();
        newsRecycler.addOnItemTouchListener(new RecyclerViewTouchListener(this, newsRecycler,
                new RecyclerViewTouchListener.OnItemClickListener(){

                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) { }
                }));
    }

    private void addNews() {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        startActivity(intent);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("news_info", null);
    }
}