package com.rippleworks.letsmeet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Splash extends AppCompatActivity {
    int SPLASH_TIMER = 4000;
    Boolean isNotFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences preferences = getSharedPreferences("my_prefs2", 0);
        isNotFirstTime = preferences.getBoolean("loggedin", false);


        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (isNotFirstTime) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(intent);
                    Splash.this.finish();
                } else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    Splash.this.startActivity(intent);
                    Splash.this.finish();
                }
            }

        }, SPLASH_TIMER);

    }
}
