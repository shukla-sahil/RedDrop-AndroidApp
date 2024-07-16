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
import android.widget.TextView;
import android.widget.Toast;

public class AfterForm extends AppCompatActivity {
TextView t;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_form);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AfterForm.this,Home2.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(AfterForm.this,Home2.class);
        startActivity(i);
    }
}