package com.example.project58;

import static com.google.firebase.auth.PhoneAuthProvider.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project58.register.DRecords;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class OTP extends AppCompatActivity {
EditText e1,e2,e3,e4,e5,e6;
TextView t,totp;
Button btn;
FirebaseAuth auth;
FirebaseDatabase database;
DatabaseReference reference;
DatabaseReference DonarRecords;
String getotpbackend;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        e1=findViewById(R.id.otp1);
        e2=findViewById(R.id.otp2);
        e3=findViewById(R.id.otp3);
        e4=findViewById(R.id.otp4);
        e5=findViewById(R.id.otp5);
        e6=findViewById(R.id.otp6);
        btn=findViewById(R.id.button);
        t=findViewById(R.id.textnumshow);

        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        DonarRecords = database.getReference().child("Donarform");
        t.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));

        getotpbackend = getIntent().getStringExtra("backendotp");
        String name=getIntent().getStringExtra("name");
        String dob=getIntent().getStringExtra("dob");
        String sex=getIntent().getStringExtra("sex");
        String group=getIntent().getStringExtra("group");
        String centre=getIntent().getStringExtra("centre");
        String date=getIntent().getStringExtra("date");
        String address=getIntent().getStringExtra("address");
        String email=getIntent().getStringExtra("email");
        String illness=getIntent().getStringExtra("illness");
        String medication=getIntent().getStringExtra("medication");
        String contact=getIntent().getStringExtra("mobile");

        final ProgressBar pb=findViewById(R.id.progressbar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!e1.getText().toString().trim().isEmpty() && !e2.getText().toString().trim().isEmpty() &&
                        !e3.getText().toString().trim().isEmpty() && !e4.getText().toString().trim().isEmpty()
                        && !e5.getText().toString().trim().isEmpty() && !e6.getText().toString().trim().isEmpty())
                {
                    String entercodeotp = e1.getText().toString() +
                            e2.getText().toString() +
                            e3.getText().toString() +
                            e4.getText().toString() +
                            e5.getText().toString() +
                            e6.getText().toString();
                    if (getotpbackend != null) {
                        pb.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(getotpbackend,entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pb.setVisibility(View.GONE);
                                        btn.setVisibility(View.VISIBLE);
                                        if(task.isSuccessful()){
                                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                                            String userId = currentUser.getUid();
                                            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
                                            DatabaseReference userRef = databaseRef.child("Donarform").child(userId);
                                            Map<String, Object> userData = new HashMap<>();
                                            userData.put("name", name);
                                            userData.put("email", email);
                                            userData.put("dob",dob);
                                            userData.put("group",group);
                                            userData.put("contact",contact);
                                            userData.put("sex",sex);
                                            userData.put("centre",centre);
                                            userData.put("date",date);
                                            userData.put("address",address);
                                            userData.put("illness",illness);
                                            userData.put("medication",medication);

                                            userRef.setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(OTP.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                         Intent i=new Intent(getApplicationContext(),AfterForm.class);
                                         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                            sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                            // Retrieve the email ID from shared preferences
                                            String emailid = sharedPreferences.getString("email","");
                                            Intent intent=getIntent();
                                            intent.putExtra("emailid",emailid);
                                            //startActivity(intent);
                                            SharedPreferences preferences = getSharedPreferences("form", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putBoolean("isUserform", true);
                                            editor.commit();
                                         startActivity(i);
                                         finish();
                                        }
                                        else{
                                            Toast.makeText(OTP.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(OTP.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(OTP.this, "Please Enter All Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        otpmove();
        totp=findViewById(R.id.textresendotp);
        totp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        OTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OTP.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend = newbackendotp;
                                Toast.makeText(OTP.this, "OTP SEND SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });

    }
    private void otpmove() {
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}