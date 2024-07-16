package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home2 extends AppCompatActivity {
TextView T1,T2,T3;
ImageButton i1,i2,i3;
ImageButton btn;
AlertDialog.Builder builder;
private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
    T1=findViewById(R.id.textView);
    T2=findViewById(R.id.textView2);
    T3=findViewById(R.id.textView3);
    i1=findViewById(R.id.imageButton);
    i2=findViewById(R.id.imageButton2);
    i3=findViewById(R.id.imageButton3);
    btn=findViewById(R.id.button3);
        builder=new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Retrieve the email ID from shared preferences
        String emailid = sharedPreferences.getString("email","");
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("form", Context.MODE_PRIVATE);
                if (preferences.contains("isUserform")) {
                    Intent intent = new Intent(Home2.this,Profile.class);
                    intent.putExtra("emailid",emailid);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Home2.this, DonarForm.class);
                    startActivity(intent);
                }
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home2.this,Donardisplayinfo.class);
                startActivity(i);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home2.this,Feedback.class);
                startActivity(intent);
            }
        });
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
                builder.setTitle("Logout").setMessage("Are you sure you want to logout?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("IsUserLogin");
                                editor.clear();
                                editor.commit();
                                finish();
                                Intent intent = new Intent(Home2.this,Home.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

    }
    public void onBackPressed() {
        // Close the app completely
        finishAffinity();
    }
}