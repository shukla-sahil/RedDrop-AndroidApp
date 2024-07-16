package com.example.project58;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.project58.register.DRecords;
import com.example.project58.register.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Donardisplayinfo extends AppCompatActivity {

    EditText searchEditText;
    Button searchButton;
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<DRecords> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donardisplayinfo);
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance().getReference("Donarform");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

searchButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String text=searchEditText.getText().toString();
        filter(text);
    }

    public void filter(String text) {
        ArrayList<DRecords> filteredlist=new ArrayList<>();
        for(DRecords item: list){
            if(item.getGroup().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        myAdapter.filterlist(filteredlist);
    }
});

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    DRecords user = dataSnapshot.getValue(DRecords.class);
                    list.add(user);


                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
