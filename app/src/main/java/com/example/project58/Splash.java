package com.example.project58;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imageView;
    Animation animation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.imageview);
        animation=AnimationUtils.loadAnimation(Splash.this,R.anim.fadein);
        imageView.setAnimation(animation);
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent send =new Intent(Splash.this,Home.class);
                startActivity(send);
                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (preferences.contains("isUserLogin")) {
                    Intent intent = new Intent(Splash.this,Home2.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Splash.this, Home.class);
                    startActivity(intent);
                }
                finish();
            }
        },2500);
    }
}