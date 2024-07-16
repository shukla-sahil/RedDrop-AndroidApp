package com.example.project58;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project58.register.DRecords;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    FirebaseUser user;
    String UserID;
    Button btn;
    TextView T1,T2,T3,T4,T5,T6,T7;
    AlertDialog.Builder builder;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        T1=findViewById(R.id.textView2);
        T2=findViewById(R.id.textView3);
        T3=findViewById(R.id.textView5);
        T4=findViewById(R.id.textView7);
        T5=findViewById(R.id.textView9);
        T6=findViewById(R.id.textView11);
        T7=findViewById(R.id.textView13);
        ref=FirebaseDatabase.getInstance().getReference("Donarform");
        btn=findViewById(R.id.button2);
        builder=new AlertDialog.Builder(this);
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
                                Intent intent = new Intent(Profile.this,Home.class);
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
        //String currentmail="heerakushwah328@gmail.com";
        Intent i=getIntent();
        String currentmail=i.getStringExtra("emailid");
ref.orderByChild("email").equalTo(currentmail).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()) {
            // Retrieve the data from the snapshot
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                DRecords donor = dataSnapshot.getValue(DRecords.class);

                // Populate the profile page with the retrieved donor data
                T1.setText(donor.getName());
                T2.setText(donor.getEmail());
                T3.setText(donor.getSex());
                T4.setText(donor.getContact());
                T5.setText(donor.getDob());
                T6.setText(donor.getGroup());
                T7.setText(donor.getAddress());

                // Continue populating other profile fields as needed
            }
        } else {
            // Handle the case when no matching donor profile is found
            Toast.makeText(Profile.this, "Donor profile not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



/*
        ref=FirebaseDatabase.getInstance().getReference("Donarform");
        user= FirebaseAuth.getInstance().getCurrentUser();
        UserID = user.getUid();

        ref.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DRecords rec=snapshot.getValue(DRecords.class);
                if(rec!=null) {
                    String name = rec.getName();
                    String email = rec.getEmail();
                    String gender = rec.getSex();
                    String phone = rec.getContact();
                    String dob = rec.getDob();
                    String blood = rec.getGroup();
                    String address = rec.getAddress();

                    T1.setText(name);
                    T2.setText(email);
                    T3.setText(gender);
                    T4.setText(phone);
                    T5.setText(dob);
                    T6.setText(blood);
                    T7.setText(address);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
*/

    }
}



/*

    String name=snapshot.child("name").getValue().toString();
    String email=snapshot.child("email").getValue().toString();
    String gender=snapshot.child("sex").getValue().toString();
    String phone=snapshot.child("contact").getValue().toString();
    String dob=snapshot.child("dob").getValue().toString();
    String blood=snapshot.child("group").getValue().toString();
    String address=snapshot.child("address").getValue().toString();
          T1.setText(name);
          T2.setText(email);
          T3.setText(gender);
          T4.setText(phone);
          T5.setText(dob);
          T6.setText(blood);
          T7.setText(address);
 */
