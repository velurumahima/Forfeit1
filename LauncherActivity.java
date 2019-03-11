package com.ocr.traffic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        TimerTask ts=new TimerTask() {
            @Override
            public void run() {
                Intent it=new Intent(LauncherActivity.this,LoginActivity.class);
                startActivity(it);
                finish();
            }
        };

        new Timer().schedule(ts,2000);
    }
}
