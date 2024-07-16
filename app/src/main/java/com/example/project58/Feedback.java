package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.example.project58.register.Feed;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
Button btn;
EditText e1,e2;
ProgressBar pb;
FirebaseDatabase database1;
DatabaseReference reference1;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn=findViewById(R.id.btn);
        pb=findViewById(R.id.progressbar);
        e1=findViewById(R.id.editTextTextEmailAddress);
        e2=findViewById(R.id.editTextTextMultiLine);
        database1= FirebaseDatabase.getInstance();
        reference1=database1.getReference("FeedBacks");
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String email=e1.getText().toString();
                String suggestion=e2.getText().toString();
                pb.setVisibility(View.VISIBLE);
                btn.setVisibility(View.INVISIBLE);
                if(email.isEmpty()){

                    pb.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);
                    e1.setError("Enter Email id");
                }
                else if (suggestion.isEmpty())
                {

                    pb.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);
                    e2.setError("Write Your FeedBack Here");
                }
                else if (isValidEmail(email)) {

                    pb.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);
                    submitFeedback(email,suggestion);
                }
                else {

                    pb.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);
                    e1.setError("Please enter a valid email address");
                }
            }

            private boolean isValidEmail(String email) {
                String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }

            private void submitFeedback(String email,String suggestion) {
                pb.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
                    Feed feed=new Feed(email,suggestion);
                    String childid= reference1.push().getKey();
                    reference1.child(childid).setValue(feed).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Feedback.this,"Feedback Sended Successfully",Toast.LENGTH_SHORT).show();
                            e1.setText("");
                            e2.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Feedback.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        });


    }
}