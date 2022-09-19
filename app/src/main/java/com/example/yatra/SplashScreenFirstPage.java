package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreenFirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {


            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(SplashScreenFirstPage.this,Login_User_orAdmin.class);
                startActivity(intent);

            }
        }.start();
    }
}