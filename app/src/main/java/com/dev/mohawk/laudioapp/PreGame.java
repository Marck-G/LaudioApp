package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

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
                saveChanges();
                Intent juegoFuentes = new Intent( PreGame.this , Fuentes.class );
                startActivity( juegoFuentes );
                finish();
            }
        } );


    }

    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.DORRETXEA ) + "1" ;
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
