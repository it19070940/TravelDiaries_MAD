package com.example.testmad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity {

    EditText name, email, mobileNo, country,password,cPassword;
    Button mRegistration;
    TextView login;
    DatabaseReference dbRef;
    Reg std;

    private CircleImageView Profile_Image;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private void clearControls(){
        name.setText("");
        email.setText("");
        mobileNo.setText("");
        country.setText("");
        password.setText("");
        cPassword.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Profile_Image = (CircleImageView) findViewById(R.id.Profile_Image);
        Profile_Image.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select picture"), PICK_IMAGE);
            }


        });


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobileNo = findViewById(R.id.mobile);
        country = findViewById(R.id.country);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.password2);
        mRegistration = findViewById(R.id.createBtn);
        login = findViewById(R.id.lBtn);

        std = new Reg();

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Registration");
                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Your Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Your Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(mobileNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the mobile no", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(country.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter country", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(password.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(cPassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();

                    else {
                        std.setName(name.getText().toString().trim());
                        std.setEmail(email.getText().toString().trim());
                        std.setMobile(mobileNo.getText().toString().trim());
                        std.setCountry(country.getText().toString().trim());
                        std.setPassword(password.getText().toString().trim());
                        std.setCPassword(cPassword.getText().toString().trim());

                        dbRef.push().setValue(std);

                        dbRef.child("std1").setValue(std);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();


                        Intent i = new Intent(Register.this, Login.class);
                        startActivity(i);
                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });





    }
}

