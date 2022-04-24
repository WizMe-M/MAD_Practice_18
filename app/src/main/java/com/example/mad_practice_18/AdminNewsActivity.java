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

        addNews.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        newsList = database.getAllNewsList();
        newsRecycler.setAdapter(new RecyclerNewsAdapter(this, newsList, this));
    }

    @Override
    public void onClick(int position) {
        NewsModel selected = newsList.get(position);
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("news_info", selected);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AuthorizationActivity.class);
        startActivity(intent);
        finish();
    }
}