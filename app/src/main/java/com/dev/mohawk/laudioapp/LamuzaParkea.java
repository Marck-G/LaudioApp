package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class LamuzaParkea extends AppCompatActivity {

    private MediaPlayer arantza19;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamuza_parkea );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        arantza19 = MediaPlayer.create(this, R.raw.arantza19); // "Reproductor del Sonido"
        arantza19.start(); // Iniciar el Sonido al iniciar la actividad
        arantza19.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion( MediaPlayer mp ) {
                Intent juego = new Intent( LamuzaParkea.this , Juego.class );
                startActivity( juego );
                finish();
            }
        } );
    }
}
