package com.example.testmad;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity  {

    //implements ImageAdaptor.OnItemClickListner

    private RecyclerView mRecyclerView;
    private ImageAdaptor mAdapter;
    private ProgressBar mProgressCircle;

    //private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    //private ValueEventListener mDBListenr;
    private List<Pack> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);
        mUploads = new ArrayList<>();

        mAdapter = new ImageAdaptor(ImagesActivity.this, mUploads) {
        };
        mRecyclerView.setAdapter(mAdapter);

        // mAdapter.setOnItemClickListner(ImagesActivity.this);
        // mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Package");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            //mDBListenr =
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Pack upload = postSnapshot.getValue(Pack.class);
                    //upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

   /* @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClicked(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
       Upload selectedItem = mUploads.get(position);
       final String selectedKey = selectedItem.getKey();

       StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
       imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               mDatabaseRef.child(selectedKey).removeValue();
               Toast.makeText(ImagesActivity.this, "Hotel Deleted", Toast.LENGTH_SHORT).show();
           }
       });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListenr);

    }*/
}