package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ImageView img;
    TextView t1,t2,t3;
    EditText E1,E2;
    Button btn;
    FirebaseAuth auth;
    ProgressDialog pd;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        img=findViewById(R.id.imageView);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        E1=findViewById(R.id.editTextTextPersonName4);
        E2=findViewById(R.id.password);
        btn=findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=E1.getText().toString();
                String password= E2.getText().toString();
                pd = new ProgressDialog(Login.this);
                pd.setCanceledOnTouchOutside(false);
                pd.setMessage("Please Wait");
                pd.show();
                if(email.isEmpty() || password.isEmpty())
                {
                    pd.dismiss();
                    Toast.makeText(Login.this, "Please Fill The Given Information", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                        @Override
                        public void onSuccess(AuthResult authResult) {
                            pd.dismiss();
                            if(auth.getCurrentUser().isEmailVerified()){
                                Intent intent=new Intent(Login.this,Home2.class);
                                //startActivity(new Intent(Login.this, Home2.class));
                                startActivity(intent);
                                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean("isUserLogin", true);
                                editor.commit();
                                sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editormail = sharedPreferences.edit();
                                editormail.putString("email", email);
                                editormail.apply();
                            }
                            else
                            {
                                pd.dismiss();
                                E1.setText("");
                                E2.setText("");
                                Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(Login.this, "Id or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Login.this, ResetPassword.class);
                startActivity(send);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Login.this, Signup.class);
                startActivity(send);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}