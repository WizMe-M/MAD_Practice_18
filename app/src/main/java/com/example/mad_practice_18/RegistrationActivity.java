package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    DatabaseHelper database;
    Button register, wantAuthorize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        database = new DatabaseHelper(this);
        wantAuthorize = findViewById(R.id.authorize_btn);
        register = findViewById(R.id.register_btn);
        wantAuthorize.setOnClickListener(v -> goToAuthorizationActivity());
        register.setOnClickListener(v -> register());
    }

    private void goToAuthorizationActivity() {
        Intent authorization = new Intent(this, AuthorizationActivity.class);
        startActivity(authorization);
        finish();
    }

    private void register() {
        EditText login, password;
        login = findViewById(R.id.login_et);
        password = findViewById(R.id.password_et);

        String l = login.getText().toString().trim();
        String p = password.getText().toString().trim();
        if(l.isEmpty() || p.isEmpty()){
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
        database.addReader(l, p);

        Intent readerNews = new Intent(this, ReaderNewsActivity.class);
        startActivity(readerNews);
    }
}