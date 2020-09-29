package com.example.testmad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class hotelMenu extends AppCompatActivity {
    RecyclerView recview;
    myadapter6 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_menu);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new
                LinearLayoutManager(this));

        FirebaseRecyclerOptions<hotelsModel> options = new FirebaseRecyclerOptions.Builder<hotelsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("uploads"), hotelsModel.class).build();

        adapter = new myadapter6(options);
        recview.setAdapter(adapter);
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