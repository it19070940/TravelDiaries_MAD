package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class hotels extends AppCompatActivity {
    RecyclerView recview;
    myadapter1 adapter;
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<hotelsModel> options = new FirebaseRecyclerOptions.Builder<hotelsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hotels"), hotelsModel.class).build();

        adapter = new myadapter1(options);
        recview.setAdapter(adapter);

        fb = (FloatingActionButton)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), addhotel.class));
            }
        });
    }


    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
}