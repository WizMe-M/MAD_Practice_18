package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminNewsActivity extends AppCompatActivity implements RecyclerNewsAdapter.NewsOnClickListener {
    DatabaseHelper database;
    ArrayList<NewsModel> newsList;
    RecyclerView newsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);

        Button addNews = findViewById(R.id.add_news_btn);
        newsRecycler = findViewById(R.id.news_list_rv);
        database = new DatabaseHelper(this);

        newsList = database.getAllNewsList();
        newsRecycler.setAdapter(new RecyclerNewsAdapter(this, newsList, this));
        addNews.setOnClickListener(v -> addNews());
    }

    @Override
    protected void onResume() {
        super.onResume();
        newsList = database.getAllNewsList();
        newsRecycler.setAdapter(new RecyclerNewsAdapter(this, newsList, this));
    }

    private void addNews() {
        Intent addNews = new Intent(this, AddNewsActivity.class);
        startActivity(addNews);
    }

    private void openNewsInfo(NewsModel model) {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("news_info", model);
        startActivity(intent);
    }

    @Override
    public void onClick(int position) {
        NewsModel selectedNews = newsList.get(position);
        openNewsInfo(selectedNews);
    }
}