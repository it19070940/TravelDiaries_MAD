package com.example.testmad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Traveller_Details extends AppCompatActivity {

    EditText name, email, mobileNo, noOfPerson,pName,feed;
    Button Add;
    DatabaseReference dbRef;
    Details std;


    private void clearControls(){
        name.setText("");
        email.setText("");
        mobileNo.setText("");
        noOfPerson.setText("");
        pName.setText("");
        feed.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveller__details);

        name = findViewById(R.id.tname);
        email = findViewById(R.id.Temail);
        mobileNo = findViewById(R.id.Tno);
        noOfPerson = findViewById(R.id.person);
        pName = findViewById(R.id.pName);
        feed = findViewById(R.id.feedback);

        Add = findViewById(R.id.addBtn);

        std = new Details();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Traveler Details");
                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Your Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Your Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(mobileNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the mobile no", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(noOfPerson.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter country", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feed.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();

                    else {
                        std.setTName(name.getText().toString().trim());
                        std.setTEmail(email.getText().toString().trim());
                        std.setTMobile(mobileNo.getText().toString().trim());
                        std.setNoOfPerson(noOfPerson.getText().toString().trim());
                        std.setPName(pName.getText().toString().trim());
                        std.setFeedback(feed.getText().toString().trim());

                        dbRef.push().setValue(std);

                        dbRef.child("std1").setValue(std);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent i = new Intent(Traveller_Details.this, hotelMenu.class);
                        startActivity(i);
                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}