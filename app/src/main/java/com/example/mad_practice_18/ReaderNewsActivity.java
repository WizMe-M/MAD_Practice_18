package com.example.mad_practice_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
public class ReaderNewsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView newsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_news);
        databaseHelper = new DatabaseHelper(this);

        if(databaseHelper.newsEmpty()){
                databaseHelper.addNews(new NewsModel("Второе пришествие", "07.04.2022", "Алексей Шубин, в народе известынй как Алёша Попович (прозвище, дарованное Соколовой) явился в техникум ради того, чтобы усердно учиться", "ТимкинМД"));
                databaseHelper.addNews(new NewsModel("Сногсшибательная блезнь", "02.04.2022", "По Канализации прошла ужасающая дух новость. Народный артист Канализации, харизматичный певец, художник, актёр Большого и Малого театра и крайне интересная личность, Стас 'Дробышевский' Гайниев слёг от неизвестной болезни и не смог прийти на семейный просмотр фильма!", "Остапенко Лев"));
                databaseHelper.addNews("Великая победа", "07.04.2022", "Андрей Кытин спустя долгого времени и ненасытного боя в великолепной, красивой игре Elden Ring, смог победить могучего врага 'Практика Щаникова' и вышел на улицу с улыбкой", "Алексей Шубин");
        }

        ArrayList<NewsModel> newsList = new ArrayList<>();
        Cursor data = databaseHelper.getNews();
        while (data.moveToNext()) {
            String header = data.getString(0);
            String text = data.getString(1);
            String datetime = data.getString(2);
            String author = data.getString(3);

            NewsModel model = new NewsModel(header, datetime, text, author);
            newsList.add(model);
        }

        newsRecycler = findViewById(R.id.news_list_rv);
        newsRecycler.setAdapter(new RecyclerNewsAdapter(getApplicationContext(), newsList));
    }
}