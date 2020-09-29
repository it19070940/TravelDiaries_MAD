package com.example.testmad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHotelActivity extends AppCompatActivity {
    ImageView fb;
    RecyclerView recview;
    myadaapter5 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new
                LinearLayoutManager(this));

        FirebaseRecyclerOptions<hotelsModel> options = new FirebaseRecyclerOptions.Builder<hotelsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("uploads"), hotelsModel.class).build();

        adapter = new myadaapter5(options);
        recview.setAdapter(adapter);

        fb= (ImageView)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), testAdd.class));
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