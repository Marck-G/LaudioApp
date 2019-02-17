package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class LamuzaParkeaIngurua extends AppCompatActivity {

    private ImageView   seguir;
    private TextView    txtSeguir;
    private MediaPlayer arantza20;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamuza_parkea_ingurua );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        seguir = findViewById( R.id.jarraitu );
        txtSeguir = findViewById( R.id.viewJarraitu );

        arantza20 = MediaPlayer.create( this, R.raw.arantza20);
        arantza20.start();
        arantza20.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seguir.setVisibility( View.VISIBLE );
                txtSeguir.setVisibility( View.VISIBLE );
            }
        });


        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextAct = new Intent( LamuzaParkeaIngurua.this, PreGame.class);
                startActivity( nextAct );
                finish();

            }
        });





    }
}
