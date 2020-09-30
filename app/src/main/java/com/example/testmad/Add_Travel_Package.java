package com.example.testmad;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Travel_Package extends AppCompatActivity {


    EditText packName, expDate, price, pack;
    Button packBtn2;
    DatabaseReference dbRef;
    Pack std;
    ProgressBar mProgressBar;
    EditText mEditTextFileName;



    private void clearControls() {
        packName.setText("");
        expDate.setText("");
        price.setText("");
        pack.setText("");
    }

    private CircleImageView Profile_Image;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__travel__package);

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


        packName = findViewById(R.id.packName);
        expDate = findViewById(R.id.expDate);
        price = findViewById(R.id.price);
        pack = findViewById(R.id.pack);

        mEditTextFileName = findViewById(R.id.packName);
        mProgressBar = findViewById(R.id.progress_bar);

        packBtn2 = findViewById(R.id.packBtn2);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        std = new Pack();

        packBtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Package1");


                {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(Add_Travel_Package.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                    } else {
                        {
                            if (imageUri != null) {
                                StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                                        + "." + getFileExtension(imageUri));
                                mUploadTask = fileReference.putFile(imageUri)
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        mProgressBar.setProgress(0);
                                                    }
                                                }, 500);
                                                Toast.makeText(Add_Travel_Package.this, "Upload successful", Toast.LENGTH_LONG).show();
                                                //Pack std = new Pack(mEditTextFileName.getText().toString().trim(),
                                                    //    taskSnapshot.getMetadata().getReference().child("Package").getDownloadUrl().toString());
                                               // std.setImageUrl(taskSnapshot.getMetadata().getReference().child("Package").getDownloadUrl().toString());
                                                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                                                while (!urlTask.isSuccessful());
                                                Uri downloadUrl = urlTask.getResult();

                                                //Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString()); //use if testing...don't need this line.
                                                Pack std = new Pack(downloadUrl.toString());
                                                //String uploadId = mDatabaseRef.push().getKey();
                                                //mDatabaseRef.child(uploadId).setValue(std);



                                                try {
                                                    if (TextUtils.isEmpty(packName.getText().toString()))
                                                        Toast.makeText(getApplicationContext(), "Please enter an package Name", Toast.LENGTH_SHORT).show();
                                                    else if (TextUtils.isEmpty(expDate.getText().toString()))
                                                        Toast.makeText(getApplicationContext(), "Please enter a Expire Date", Toast.LENGTH_SHORT).show();
                                                    else if (TextUtils.isEmpty(price.getText().toString()))
                                                        Toast.makeText(getApplicationContext(), "Please enter an Price", Toast.LENGTH_SHORT).show();
                                                    else if (TextUtils.isEmpty(pack.getText().toString()))
                                                        Toast.makeText(getApplicationContext(), "Please enter an Package", Toast.LENGTH_SHORT).show();
                                                    else {
                                                        std.settName(packName.getText().toString().trim());
                                                        std.settDate(expDate.getText().toString().trim());
                                                        std.settPrice(price.getText().toString().trim());
                                                        std.settPackage(pack.getText().toString().trim());

                                                        mDatabaseRef.push().setValue(std);

                                                        mDatabaseRef.child("std1").setValue(std);
                                                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                                                        clearControls();

                                                        Intent i = new Intent(Add_Travel_Package.this, ImagesActivity.class);
                                                        startActivity(i);
                                                    }


                                                } catch (NumberFormatException e) {
                                                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Add_Travel_Package.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                                mProgressBar.setProgress((int) progress);
                                            }
                                        });
                            }
                        }
                    }
                }





            }


        });
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE&& resultCode==RESULT_OK){
            imageUri=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Profile_Image.setImageBitmap(bitmap);
            }catch (IOException o){
                o.printStackTrace();
            }
        }

    }


}

