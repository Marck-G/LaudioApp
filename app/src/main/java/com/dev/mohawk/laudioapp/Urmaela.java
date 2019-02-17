package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Urmaela extends AppCompatActivity {


    private Button sakatu;
    private TextView txtBocata;
    private ImageView imgTxomin , bocata;
    private MediaPlayer txomin21;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_urmaela );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        txtBocata = findViewById( R.id.urmaelaTxt );
        imgTxomin = findViewById( R.id.urmaelaTxomin );
        bocata = findViewById( R.id.urmaelaIMG );
        txomin21 = MediaPlayer.create( this , R.raw.txomin21 );



        sakatu = findViewById( R.id.botSakatu );
        sakatu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sakatu.setVisibility( View.INVISIBLE );
                imgTxomin.setVisibility( View.VISIBLE );
                txtBocata.setVisibility( View.VISIBLE );
                bocata.setVisibility( View.VISIBLE );

                txomin21.start();
                txomin21.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent skate = new Intent( Urmaela.this , Skate.class );
                        startActivity( skate );
                        finish();
                    }
                });


            }
        } );


    }
}
