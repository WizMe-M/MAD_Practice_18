package com.example.mad_practice_18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NewsInfoActivity extends AppCompatActivity {

    NewsModel news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        news = savedInstanceState.getParcelable("news_info");

        TextView header = findViewById(R.id.header_tv);
        header.setText(news.Header);
        TextView text = findViewById(R.id.main_text_tv);
        text.setText(news.Header);
        TextView datetime = findViewById(R.id.datetime_tv);
        datetime.setText(news.Header);
        TextView author = findViewById(R.id.author_tv);
        author.setText(news.Header);

        Button edit = findViewById(R.id.edit_btn);
        edit.setOnClickListener(view -> {
            Intent editNews = new Intent(this, EditNewsActivity.class);
//            editNews.putExtra("news_info", news);
            startActivity(editNews);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("news_info", news);
    }
}