package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminNewsActivity extends AppCompatActivity {

    Button addNews;
    RecyclerView newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);
    }

    private void addNews() {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        startActivity(intent);
    }
}