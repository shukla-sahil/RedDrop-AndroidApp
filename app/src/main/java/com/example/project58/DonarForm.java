package com.example.project58;

import static com.google.firebase.auth.PhoneAuthProvider.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project58.register.DRecords;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class DonarForm extends AppCompatActivity {
DatabaseReference DonarRecords;
ImageView I1;
TextView dt,dt1;
EditText E1,E2,E3,E4,E5,E6,E7;
Spinner S1,S2,S3;
CheckBox C1;
Button btn;
ProgressBar pb;
int day,month,year;
FirebaseUser user;
String UserID;
FirebaseAuth auth;
FirebaseDatabase database;
DatabaseReference reference;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_form);

        I1 = findViewById(R.id.image_logo);
        E1 = findViewById(R.id.input_fullName);
        E2 = findViewById(R.id.inputMobile);
        E3 = findViewById(R.id.inputAddress);
        dt = findViewById(R.id.editTextDate);
        dt1 = findViewById(R.id.editTextDate1);
        E4 = findViewById(R.id.input_userEmail);
        E5 = findViewById(R.id.illness);
        E6 = findViewById(R.id.medication);
        S1 = findViewById(R.id.gender);
        S2 = findViewById(R.id.inputBloodGroup);
        S3 = findViewById(R.id.center);
        btn = findViewById(R.id.button);
        C1 = findViewById(R.id.checkbox);
        pb=findViewById(R.id.progressbar);
        C1=findViewById(R.id.checkbox);
        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Retrieve the email ID from shared preferences
        String emailid = sharedPreferences.getString("email","");
        DonarRecords = database.getReference().child("Donarform");
        //Date picker dialog
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DonarForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Calculate the user's age
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        Calendar currentDate = Calendar.getInstance();
                        int age = currentDate.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR);
                        if (age>18) {
                            String selectedDateString = dayOfMonth + "/" +(month + 1)  + "/" + year;
                            dt.setText(selectedDateString);
                        } else {
                            Toast.makeText(DonarForm.this, "Your age must be above 18 to become a donor" , Toast.LENGTH_SHORT).show();
                            dt.setText("");
                        }
                    }
                }, 2000, 0, 1); // Set an initial date in the date picker dialog
                datePickerDialog.show();
            }
        });
        dt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar ca = Calendar.getInstance();
                day = ca.get(Calendar.DAY_OF_MONTH);
                month = ca.get(Calendar.MONTH);
                year = ca.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DonarForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        Calendar currentDate = Calendar.getInstance();
                        String selectedDateString = dayOfMonth + "/" +(month + 1)  + "/" + year;
                        dt1.setText(selectedDateString);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(ca.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        /*DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dt.setText(selectedDate);

            }
        },
                year, month, day
        );
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
*/
/*
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(day, month, year);
        Calendar currentDate = Calendar.getInstance();
        LocalDate selectedLocalDate = LocalDate.of(day, month + 1, year);
        LocalDate currentLocalDate = LocalDate.of(currentDate.get(Calendar.DAY_OF_MONTH),
                currentDate.get(Calendar.MONTH) + 1,
                currentDate.get(Calendar.YEAR));
        Period ageDifference = Period.between(selectedLocalDate, currentLocalDate);
        int age = ageDifference.getYears();
        Toast.makeText(DonarForm.this, "Your Age: "+ age, Toast.LENGTH_SHORT).show();
*//*

        //Date picker dialog1
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dt1.setText(selectedDate);
            }
        },
                year, month, day
        );

        dt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog1.show();
            }
        });

*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= E1.getText().toString();
                String dob= dt.getText().toString();
                String sex=  S1.getSelectedItem().toString();
                String group=  S2.getSelectedItem().toString();
                String contact= E2.getText().toString();
                String centre=  S3.getSelectedItem().toString();
                String date= dt1.getText().toString();
                String address= E3.getText().toString();
                String email= E4.getText().toString();
                String illness= E5.getText().toString();
                String medication= E6.getText().toString();
                if(name.isEmpty())
                {
                    Toast.makeText(DonarForm.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if(dob.isEmpty())
                {
                    Toast.makeText(DonarForm.this, "Please Enter Your Date Of Birth", Toast.LENGTH_SHORT).show();
                }
                else if(date.isEmpty())
                {
                    Toast.makeText(DonarForm.this, "Please Enter Prefered Date", Toast.LENGTH_SHORT).show();
                }

                else if(address.isEmpty())
                {
                    Toast.makeText(DonarForm.this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
                }

                else if(email.isEmpty())
                {
                    Toast.makeText(DonarForm.this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                }

                else if(illness.isEmpty())
                {
                    E5.setError("Please Mention Yes/No");
                }

                else if(medication.isEmpty())
                {
                    E6.setError("Please Mention Yes/No");
                }
                else if(!C1.isChecked()){
                    Toast.makeText(DonarForm.this, "You must accept the terms and conditions to register", Toast.LENGTH_SHORT).show();
                }
                else{

                    if (!E2.getText().toString().trim().isEmpty()) {
                        if ((E2.getText().toString().trim()).length() == 10) {
                            pb.setVisibility(View.VISIBLE);
                            btn.setVisibility(View.INVISIBLE);

                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+91" + E2.getText().toString(),
                                    60,
                                    TimeUnit.SECONDS,
                                    DonarForm.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            pb.setVisibility(View.GONE);
                                            btn.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            pb.setVisibility(View.GONE);
                                            btn.setVisibility(View.VISIBLE);
                                            Toast.makeText(DonarForm.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            pb.setVisibility(View.GONE);
                                            btn.setVisibility(View.VISIBLE);
                                            Intent i = new Intent(getApplicationContext(), OTP.class);
                                            i.putExtra("mobile", E2.getText().toString());
                                            i.putExtra("name",name);
                                            i.putExtra("dob",dob);
                                            i.putExtra("sex",sex);
                                            i.putExtra("group",group);
                                            i.putExtra("centre",centre);
                                            i.putExtra("date",date);
                                            i.putExtra("address",address);
                                            i.putExtra("email",email);
                                            i.putExtra("illness",illness);
                                            i.putExtra("medication",medication);
                                            i.putExtra("backendotp",backendotp);
                                            startActivity(i);
                                        }
                                    }
                            );
                        } else {
                            Toast.makeText(DonarForm.this, "Please Enter Correct Number.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(DonarForm.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });
    }
}