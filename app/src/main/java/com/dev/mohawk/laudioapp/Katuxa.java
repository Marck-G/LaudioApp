package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.marck.renfeApi.RenfeRequest;
import com.marck.renfeApi.params.RequestParams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Katuxa extends AppCompatActivity {

    private MediaPlayer arantza18;
    public static final String LLODIO_ST_H_FILE = "llodio_st.h";
    public static final String ST_LLODIO_H_FILE = "st_llodio.h";
    public static final String MAP_OFFLINE_FILE = "mbgl-offline.db";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_katuxa );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ;

        arantza18 = MediaPlayer.create(this, R.raw.arantza18); // "Reproductor del Sonido"
        arantza18.start(); // Iniciar el Sonido al iniciar la actividad

        arantza18.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion( MediaPlayer mp ) {
                finish();
                Intent lorategia = new Intent( Katuxa.this , Lorategia.class );
                startActivity( lorategia );
                finish();
            }
        });

    }

}
