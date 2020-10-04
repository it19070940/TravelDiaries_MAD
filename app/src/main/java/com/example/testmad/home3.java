package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home3 extends AppCompatActivity {
    public CardView card1, card2, card3, card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        card1 = (CardView)findViewById(R.id.c1);
        card2 = (CardView)findViewById(R.id.c2);
        card3 = (CardView)findViewById(R.id.c3);
        card4 = (CardView)findViewById(R.id.c4);

        card1.setOnClickListener((View.OnClickListener) this);
        card2.setOnClickListener((View.OnClickListener) this);
        card3.setOnClickListener((View.OnClickListener) this);
        card4.setOnClickListener((View.OnClickListener) this);
    }


    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.c1:
                i = new Intent(this, ImagesActivity.class);
                startActivity(i);
                break;
            case R.id.c2:
                i = new Intent(this, hotelMenu.class);
                startActivity(i);
                break;
            case R.id.c3:
                i = new Intent(this, travelplanmenu.class);
                startActivity(i);
                break;
            case R.id.c4:
                i = new Intent(this, logout.class);
                startActivity(i);
                break;
        }

    }

}