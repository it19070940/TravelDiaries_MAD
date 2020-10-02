package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Addplan extends AppCompatActivity {

    private Button button;
    Button viewtrav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);

        button = (Button)findViewById(R.id.addtravel);
        viewtrav =(Button)findViewById(R.id.viewplan);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdd();
            }
        });

        viewtrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addplan.this,viewplan.class));
                finish();
            }
        });
    }
    public void openAdd(){
        Intent intent = new  Intent(this, addtravelplan.class);
        startActivity(intent);
    }
}