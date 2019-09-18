package com.tongcheng.soothsay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //使用Handler发送延迟消息
//        new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
//                startActivity(intent);
//                finish();
//                return false;
//            }
//        }).sendEmptyMessageDelayed(0, 3000);
        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
        finish();

    }
}


