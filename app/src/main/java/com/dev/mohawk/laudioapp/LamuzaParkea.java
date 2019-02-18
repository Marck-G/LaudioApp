package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

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
                saveChanges();
                Intent juego = new Intent( LamuzaParkea.this , Juego.class );
                startActivity( juego );
                finish();
            }
        } );
    }
    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.PARKE ) + "2" ;
        Log.e( "DB", m.toString() );
//        actualizamos la base de datos
        m.updateLastPoint( Integer.parseInt( id ) );
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
        finish();
    }
}
