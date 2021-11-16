package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.ui.login.ProgressActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);
                    Intent i = new Intent(getApplicationContext(), ProgressActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }
}