package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class FinJuego extends AppCompatActivity {

    private MediaPlayer txomin19;
    private boolean repitiendo = false;
    private ImageView jarraitu , repetir;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fin_juego );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        jarraitu = findViewById( R.id.jarraitu );
        jarraitu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                continuar( );
            }
        } );


        repetir = findViewById( R.id.errepikatu );
        repetir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
            repetir( );
            }
        });

        txomin19 = MediaPlayer.create( this , R.raw.txomin19 ); // Reproductor del Sonido

            if(!repitiendo) {
                txomin19.start();
            }
    }

    public void continuar(  ) {
        saveChanges();
        Intent lamuza = new Intent( FinJuego.this, LamuzaParkeaIngurua.class );
        startActivity( lamuza );
        finish();
    }

    public void repetir( ) {
        Intent juego = new Intent( FinJuego.this, Juego.class );
        startActivity( juego );
        finish();
        repitiendo = true;
    }
    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.PARKE ) + "4" ;
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
