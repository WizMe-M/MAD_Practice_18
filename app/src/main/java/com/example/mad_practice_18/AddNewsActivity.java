package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewsActivity extends AppCompatActivity {

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        database = new DatabaseHelper(this);
        Button addNews = findViewById(R.id.add_news_btn);
        addNews.setOnClickListener(v -> addNews());
    }

    public void addNews() {
        EditText header, text, date, author;
        header = findViewById(R.id.header_et);
        text = findViewById(R.id.main_text_et);
        date = findViewById(R.id.datetime_et);
        author = findViewById(R.id.author_et);

        String h = header.getText().toString().trim();
        String t = text.getText().toString().trim();
        String d = date.getText().toString().trim();
        String a = author.getText().toString().trim();
        if (h.isEmpty() || t.isEmpty() || d.isEmpty() || a.isEmpty()) {
            Toast.makeText(this, "Необходимо заполнить все поля",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        database.addNews(h, t, d, a);
        Intent adminNews = new Intent(this, AdminNewsActivity.class);
        startActivity(adminNews);
        finish();
    }
}