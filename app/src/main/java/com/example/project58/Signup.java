package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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

import com.example.project58.register.Records;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    ImageView img;
    TextView t1;
    EditText E1,E2,E3,E4,E5;
    Button btn;
    FirebaseUser user;
    String UserID;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    ProgressDialog pd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        img = findViewById(R.id.imageView);
        t1 = findViewById(R.id.textView);
        E1 = findViewById(R.id.editTextTextPersonName1);
        E2 = findViewById(R.id.editTextTextPersonName2);
        E3 = findViewById(R.id.editTextTextPersonName3);
        E4 = findViewById(R.id.password1);
        E5 = findViewById(R.id.password2);
        btn = findViewById(R.id.button);
        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User Details");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = E2.getText().toString();
                String name = E1.getText().toString();
                String phone = E3.getText().toString();
                String password = E4.getText().toString();
                String checkpassword = E5.getText().toString();
                pd = new ProgressDialog(Signup.this);
                pd.setMessage("Please Wait");
                pd.show();
                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || checkpassword.isEmpty()) {
                    pd.dismiss();
                    Toast.makeText(Signup.this, "Fill the given information", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length() != 10) {
                    pd.dismiss();
                    Toast.makeText(Signup.this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(checkpassword)) {
                    pd.dismiss();
                    Toast.makeText(Signup.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Records records = new Records(name, email, phone);
                            user= FirebaseAuth.getInstance().getCurrentUser();
                            UserID = user.getUid();
                            String childID = reference.push().getKey();
                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        reference.child(UserID).setValue(records).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                pd.dismiss();
                                                E1.setText("");
                                                E2.setText("");
                                                E3.setText("");
                                                E4.setText("");
                                                E5.setText("");
                                                Toast.makeText(Signup.this, "Register Successfully!! Verify your email", Toast.LENGTH_LONG).show();
                                                Intent i=new Intent(Signup.this,Login.class);
                                                startActivity(i);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                pd.dismiss();
                                                Toast.makeText(Signup.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(Signup.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
    }
}
