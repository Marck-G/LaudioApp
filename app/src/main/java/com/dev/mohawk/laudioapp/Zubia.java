package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Zubia extends AppCompatActivity {


    private Button sakatu;
    private TextView txtBocata;
    private ImageView imgTxomin , bocata, errepide , zubi;
    private MediaPlayer txomin22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zubia);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        txtBocata = findViewById( R.id.zubiaTxt );
        imgTxomin = findViewById( R.id.zubiaTxomin );
        bocata = findViewById( R.id.zubiaIMG );
        errepide = findViewById( R.id.errepidea );
        zubi = findViewById( R.id.zubia );
        txomin22 = MediaPlayer.create( this , R.raw.txomin22 );


        sakatu = findViewById( R.id.botSakatu );
        sakatu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sakatu.setVisibility( View.INVISIBLE );
                zubi.setVisibility( View.INVISIBLE );
                errepide.setVisibility( View.VISIBLE );
                imgTxomin.setVisibility( View.VISIBLE );
                txtBocata.setVisibility( View.VISIBLE );
                bocata.setVisibility( View.VISIBLE );

                txomin22.start();
                txomin22.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent gudari = new Intent( Zubia.this , Gudariak.class );
                        startActivity( gudari );
                        finish();
                    }
                });


            }
        } );
    }
}
