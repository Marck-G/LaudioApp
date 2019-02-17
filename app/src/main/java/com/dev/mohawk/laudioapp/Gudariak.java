package com.dev.mohawk.laudioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Gudariak extends AppCompatActivity {


    private ImageView gudari;
    private LinearLayout gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudariak);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gps = findViewById( R.id.lMapa );
        gps.setVisibility( View.INVISIBLE );

        gudari = findViewById( R.id.gudariak );
        gudari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gudari.setVisibility( View.INVISIBLE );
                gps.setVisibility( View.VISIBLE );

            }
        });


    }
}
