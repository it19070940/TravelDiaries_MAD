package com.example.testmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.PriorityQueue;

public class users extends AppCompatActivity {

   RecyclerView recview;
   myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ProductsModel> options = new FirebaseRecyclerOptions.Builder<ProductsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), ProductsModel.class).build();

        adapter = new myadapter(options);
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