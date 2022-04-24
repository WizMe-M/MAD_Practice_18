package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNewsActivity extends AppCompatActivity {

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);

        database = new DatabaseHelper(this);
        Button saveChanges;
        NewsModel news = getIntent().getParcelableExtra("news_to_edit");
        EditText header = findViewById(R.id.header_et);
        EditText text = findViewById(R.id.main_text_et);
        EditText datetime = findViewById(R.id.datetime_et);
        EditText author = findViewById(R.id.author_et);
        saveChanges = findViewById(R.id.save_changes_btn);

        header.setText(news.Header);
        text.setText(news.MainText);
        datetime.setText(news.Date);
        author.setText(news.Author);

        saveChanges.setOnClickListener(v -> {
            String h = header.getText().toString().trim();
            String t = text.getText().toString().trim();
            String d = datetime.getText().toString().trim();
            String a = author.getText().toString().trim();
            if (h.isEmpty() || t.isEmpty() || d.isEmpty() || a.isEmpty()) {
                Toast.makeText(this, "Необходимо заполнить все поля",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            news.Header = h;
            news.MainText = t;
            news.Date = d;
            news.Author = a;
            database.updateNews(news);
            Intent intent = new Intent(this, AdminNewsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}