package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsInfoActivity extends AppCompatActivity {

    DatabaseHelper database;
    NewsModel news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        database = new DatabaseHelper(this);
        TextView header = findViewById(R.id.header_tv);
        TextView text = findViewById(R.id.main_text_tv);
        TextView datetime = findViewById(R.id.datetime_tv);
        TextView author = findViewById(R.id.author_tv);
        Button edit = findViewById(R.id.edit_btn);
        Button delete = findViewById(R.id.delete_btn);
        news = getIntent().getParcelableExtra("news_info");

        header.setText(news.Header);
        text.setText(news.MainText);
        datetime.setText(news.Date);
        author.setText(news.Author);

        edit.setOnClickListener(view -> {
            Intent editNews = new Intent(this, EditNewsActivity.class);
            editNews.putExtra("news_to_edit", news);
            startActivity(editNews);
        });

        delete.setOnClickListener(view -> {
            database.deleteNews(news);
            Intent adminNews = new Intent(this, AdminNewsActivity.class);
            startActivity(adminNews);
            finish();
        });
    }
}