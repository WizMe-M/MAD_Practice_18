package com.example.mad_practice_18;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        long loadingMillis = 2700;

        Intent authorization = new Intent(this, AuthorizationActivity.class);
        CountDownTimer timer = new CountDownTimer(loadingMillis, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                double percent = 1 - 1.0 * millisUntilFinished / loadingMillis;
                int value = (int) (percent * 100) + 1;
                progressBar.setProgress(value);
            }

            @Override
            public void onFinish() {
                startActivity(authorization);
            }
        };
        timer.start();
    }
}