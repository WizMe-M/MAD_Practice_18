package com.example.mad_practice_18;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsInfoActivity extends AppCompatActivity {

    NewsModel news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        TextView header = findViewById(R.id.header_tv);
        TextView text = findViewById(R.id.main_text_tv);
        TextView datetime = findViewById(R.id.datetime_tv);
        TextView author = findViewById(R.id.author_tv);
        Button edit = findViewById(R.id.edit_btn);
        news = getIntent().getParcelableExtra("news_info");

        header.setText(news.Header);
        text.setText(news.Text);
        datetime.setText(news.Datetime);
        author.setText(news.Author);

        edit.setOnClickListener(view -> {
        });
    }
}