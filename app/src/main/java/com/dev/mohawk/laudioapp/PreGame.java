package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class PreGame extends AppCompatActivity {

    private MediaPlayer txomin20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txomin20 = MediaPlayer.create( this , R.raw.txomin20 );
        txomin20.start();

        txomin20.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion( MediaPlayer mp ) {
                Intent juegoFuentes = new Intent( PreGame.this , Fuentes.class );
                startActivity( juegoFuentes );
                finish();
            }
        } );


    }
}
