package com.example.project58;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView T;
    ImageView img;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        T=findViewById(R.id.textView);
        img=findViewById(R.id.imageView);
        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Login.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, Signup.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Close the app completely
        finishAffinity();
    }
}