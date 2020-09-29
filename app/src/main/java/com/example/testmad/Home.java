package com.example.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Home extends AppCompatActivity {
    MeowBottomNavigation meo;
    private final static int ID_HOME =1;
    private final static int ID_HOTEL =2;
    private final static int ID_PROMOTION =3;
    private final static int ID_LOGOUT =4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        meo = (MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        meo.add(new MeowBottomNavigation.Model(2, R.drawable.hotel));
        meo.add(new MeowBottomNavigation.Model(3, R.drawable.promotion));
        meo.add(new MeowBottomNavigation.Model(4, R.drawable.logout));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentProfile()).commit();
        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "Clicked item" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_fragment = null;
                switch (item.getId()){
                    case ID_HOME:
                        select_fragment= new FragmentProfile();
                        break;
                    case ID_HOTEL:
                        select_fragment= new FragmentHotel();
                        break;
                    case ID_PROMOTION:
                        select_fragment= new FragmentProfile();
                        break;
                    case ID_LOGOUT:
                        select_fragment= new FragmentProfile();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select_fragment).commit();
            }
        });

    }




}

