package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void lanzarMapa (View view){
        // LANZAMOS EL MAPA
        Intent intento = new Intent(this,LevelChooser.class);
        startActivity(intento);
    }

    public void cerrarApp (View view){

        finish();
    }
}
