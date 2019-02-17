package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class FinFuentes extends AppCompatActivity {

    private MediaPlayer arantza21;
    private boolean repitiendo = false;
    private ImageView jarraitu , repetir;
    private TextView txtJar , txtRep;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fin_fuentes );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arantza21 = MediaPlayer.create( this , R.raw.arantza21 );
        txtJar = findViewById( R.id.txtJarraitu );
        txtRep = findViewById( R.id.txtErrepi );

        if(!repitiendo) {
            arantza21.start();
        }

        arantza21.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion( MediaPlayer mp ) {
                jarraitu.setVisibility( View.VISIBLE );
                repetir.setVisibility( View.VISIBLE );
                txtJar.setVisibility( View.VISIBLE );
                txtRep.setVisibility( View.VISIBLE );
            }
        });

        jarraitu = findViewById( R.id.jarraitu );
        jarraitu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                continuar( );
            }
        });

        repetir = findViewById( R.id.errepikatu );
        repetir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                repetir( );
            }
        });


    }

    public void continuar(  ) {
        Intent lamuza = new Intent( FinFuentes.this, Urmaela.class );
        startActivity( lamuza );
        finish();
    }

    public void repetir( ) {
        Intent juego = new Intent( FinFuentes.this, Fuentes.class );
        startActivity( juego );
        finish();
        repitiendo = true;
    }
}
