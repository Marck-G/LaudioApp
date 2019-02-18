package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

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
                saveChanges();
                Intent nextAct = new Intent( LamuzaParkeaIngurua.this, PreGame.class);
                startActivity( nextAct );
                finish();

            }
        });

    }
    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.PARKE ) + "5" ;
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
