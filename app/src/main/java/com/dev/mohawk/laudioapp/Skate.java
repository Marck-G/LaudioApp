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

public class Skate extends AppCompatActivity {

    private Button sakatu;
    private TextView txtBocata;
    private ImageView imgArantza , bocata;
    private MediaPlayer arantza22;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_skate );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        txtBocata = findViewById( R.id.skateText );
        imgArantza = findViewById( R.id.skateArantza );
        bocata = findViewById( R.id.skateImg );
        arantza22 = MediaPlayer.create( this , R.raw.arantza22 );


        sakatu = findViewById( R.id.botSakatu );
        sakatu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sakatu.setVisibility( View.INVISIBLE );
                imgArantza.setVisibility( View.VISIBLE );
                txtBocata.setVisibility( View.VISIBLE );
                bocata.setVisibility( View.VISIBLE );

                arantza22.start();
                arantza22.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent zubia = new Intent( Skate.this , Zubia.class );
                        startActivity( zubia );
                        finish();
                    }
                });


            }
        } );



    }


}
