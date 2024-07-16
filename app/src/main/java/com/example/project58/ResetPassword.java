package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    Button btn;
    EditText et;
    ProgressBar pb;
    FirebaseAuth auth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        btn = findViewById(R.id.button);
        et = findViewById(R.id.editTextTextPersonName4);
        pb = findViewById(R.id.progressbar);
        auth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    ResetPassword();
                } else {
                    Toast.makeText(ResetPassword.this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void ResetPassword()
    {
        pb.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ResetPassword.this, "Reset Password Link has beeen sent to your registered Email", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(ResetPassword.this,Login.class);
                startActivity(i);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ResetPassword.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.VISIBLE);
            }
        });
    }

}