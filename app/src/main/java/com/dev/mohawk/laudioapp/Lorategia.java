package com.dev.mohawk.laudioapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;


public class Lorategia extends AppCompatActivity {

    private ImageButton botonImagen;
    private Button botonTren;
    private ImageView imgTxomin;
    private LinearLayout mapa;
    private MediaPlayer txomin18;
    private static Lorategia lor;



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lorategia );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        lor = this;

        botonImagen  = ( ImageButton ) findViewById( R.id.lorategia2 );
        botonTren= ( Button ) findViewById( R.id.botTren );
        botonTren.setVisibility( View.INVISIBLE );

        imgTxomin = (ImageView ) findViewById( R.id.imgTxomin );
        imgTxomin.setVisibility( View.INVISIBLE );

        mapa = findViewById( R.id.lMapa );
        mapa.setVisibility( View.INVISIBLE );

        txomin18 = MediaPlayer.create( this, R.raw.txomin18 ); // Reproductor del Sonido

        botonImagen.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                mapa.setVisibility( View.VISIBLE );

                new Handler(  ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        imgTxomin.setVisibility( View.VISIBLE );
                        txomin18.start();

                        txomin18.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion( MediaPlayer mp ) {
                                botonTren.setVisibility( View.VISIBLE );
                            }
                        } );
                    }
                },1500 );

            }
        } );
    }

    public void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.PARKE ) + "1" ;
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

    public static Lorategia getLor() {
        return lor;
    }
}
