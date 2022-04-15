package com.example.mad_practice_sqlitenews;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView newsRecycler;
    ArrayList<NewsModel> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        databaseHelper.insert("Hello", "Sanya cool","Sanyo", "Alexandr");
        select();


        news.add(new NewsModel("Второе пришествие", "07.04.2022", "Алексей Шубин, в народе известынй как Алёша Попович (прозвище, дарованное Соколовой) явился в техникум ради того, чтобы усердно учиться", "ТимкинМД"));
        news.add(new NewsModel("Сногсшибательная блезнь", "02.04.2022", "По Канализации прошла ужасающая дух новость. Народный артист Канализации, харизматичный певец, художник, актёр Большого и Малого театра и крайне интересная личность, Стас 'Дробышевский' Гайниев слёг от неизвестной болезни и не смог прийти на семейный просмотр фильма!", "Остапенко Лев"));
        news.add(new NewsModel("Великая победа", "07.04.2022", "Андрей Кытин спустя долгого времени и ненасытного боя в великолепной, красивой игре Elden Ring, смог победить могучего врага 'Практика Щаникова' и вышел на улицу с улыбкой", "Алексей Шубин"));

        newsRecycler = findViewById(R.id.news_list);
        newsRecycler.setAdapter(new RecyclerNewsAdapter(getApplicationContext(), news));
    }

    private void select() {
        Cursor res = databaseHelper.getData();
        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(),
                    "База данных пуста",
                    Toast.LENGTH_LONG).show();
            return;
        }
        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            String header = res.getString(0);
            String datetime = res.getString(1);
            String text = res.getString(2);
            String author = res.getString(3);
            news.add(new NewsModel(header,datetime,text,author));
        }
    }
}