package com.example.testmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class viewplan extends AppCompatActivity {

    FloatingActionButton faddbtn;
    FloatingActionButton fbackbtn;
    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;

    Customadapter adapter;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewplan);

        db= FirebaseFirestore.getInstance();

        mRecyclerView = findViewById(R.id.recycleview);
        faddbtn = findViewById(R.id.naddbtn);
        fbackbtn = findViewById(R.id.nbackbtn);

        pd = new ProgressDialog(this);

        faddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewplan.this,addtravelplan.class));
                finish();
            }
        });

        fbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewplan.this, Addplan.class));
                finish();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        pd.setTitle("Loading...");
        pd.show();

        db.collection("Documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        Toast.makeText(viewplan.this,"Successfully Viewed",Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot doc: task.getResult()){
                            Model model = new Model(doc.getString("ID"), doc.getString("DateFrom"),doc.getString("DateTo"),doc.getString("DestinationFrom"),doc.getString("DestinationTo"),doc.getString("TypeOfTravel"),doc.getString("TypeOFHotel"),doc.getLong("NumberOfPeople").intValue(),doc.getLong("Budget").doubleValue());
                            modelList.add(model);
                        }

                        adapter = new Customadapter(viewplan.this,modelList);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(viewplan.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deletedata(int index){
        pd.setTitle("Deleting...");
        pd.show();

        db.collection("Documents").document(modelList.get(index).getIDtravel()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(viewplan.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                        showData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(viewplan.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}