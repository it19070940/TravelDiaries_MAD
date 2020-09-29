package com.example.testmad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class testAdd extends AppCompatActivity {
    EditText name, rate, details, url;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add);

        name = (EditText)findViewById(R.id.hotelName);
        rate = (EditText)findViewById(R.id.hotelRate);
        details= (EditText)findViewById(R.id.hotelDetails);
        url = (EditText)findViewById(R.id.hotelImg);

        /*back = (ImageView)findViewById(R.id.close);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });*/

        submit = (Button)findViewById(R.id.upload);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("rate", rate.getText().toString());
        map.put("details", details.getText().toString());
        map.put("imageUrl", url.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("uploads").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        rate.setText("");
                        details.setText("");
                        url.setText("");
                        Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();

                    }
                });
    }

}
