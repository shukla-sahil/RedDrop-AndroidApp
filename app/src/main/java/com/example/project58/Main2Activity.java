package com.example.project58;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class Main2Activity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        t1=(TextView) findViewById(R.id.n);
        t2=(TextView) findViewById(R.id.d);
        t3=(TextView) findViewById(R.id.s);
        t4=(TextView) findViewById(R.id.g);
        t5=(TextView) findViewById(R.id.co);
        t6=(TextView) findViewById(R.id.ce);
        t7=(TextView) findViewById(R.id.da);
        t8=(TextView) findViewById(R.id.ad);
        t9=(TextView) findViewById(R.id.em);
        t10=(TextView) findViewById(R.id.il);
        t11=(TextView) findViewById(R.id.me);
        btn=(Button) findViewById(R.id.but);


        t1.setText(getIntent().getStringExtra("name"));
        t2.setText(getIntent().getStringExtra("dob"));
        t3.setText(getIntent().getStringExtra("sex"));
        t4.setText(getIntent().getStringExtra("group"));
        t5.setText(getIntent().getStringExtra("cont"));
        t6.setText(getIntent().getStringExtra("centre"));
        t7.setText(getIntent().getStringExtra("date"));
        t8.setText(getIntent().getStringExtra("address"));
        t9.setText(getIntent().getStringExtra("email"));
        t10.setText(getIntent().getStringExtra("illness"));
        t11.setText(getIntent().getStringExtra("medication"));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = getIntent().getStringExtra("cont");
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
            }
        });




    }
}