package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorizationActivity extends AppCompatActivity {
    DatabaseHelper database;
    Button wantRegister, authorize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        database = new DatabaseHelper(this);
        wantRegister = findViewById(R.id.register_btn);
        authorize = findViewById(R.id.authorize_btn);

        wantRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });
        authorize.setOnClickListener(v -> authorize());
    }

    private void authorize() {
        EditText login, password;
        login = findViewById(R.id.login_et);
        password = findViewById(R.id.password_et);

        String l = login.getText().toString().trim();
        String p = password.getText().toString();
        UserModel user = database.findUser(l, p);
        if (user == null) {
            Toast.makeText(this, "Пользователя с такими данными нет в базе данных",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent newsActivity;
        if (user.IsAdmin) {
            newsActivity = new Intent(this, AdminNewsActivity.class);
        } else {
            newsActivity = new Intent(this, ReaderNewsActivity.class);
        }
        startActivity(newsActivity);
    }
}