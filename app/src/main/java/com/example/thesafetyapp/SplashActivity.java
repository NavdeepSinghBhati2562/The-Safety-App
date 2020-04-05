package com.example.thesafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
                int flag = preferences.getInt("flag", 0);

                Intent iNext;
                if (flag==0){
                    iNext = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    iNext = new Intent(SplashActivity.this, Home.class);
                }
                startActivity(iNext);

            }
        },1000);
    }

}
