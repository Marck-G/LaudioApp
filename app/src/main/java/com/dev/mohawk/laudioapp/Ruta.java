package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class Ruta extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ruta );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent ruta = new Intent( Ruta.this , LamuzaParkea.class );
                startActivity( ruta );
                finish();
            }
        },3000 );
    }
}
