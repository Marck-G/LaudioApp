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

public class Fuentes extends AppCompatActivity {


    private ImageView foto1 , foto2 , foto3 , foto4;
    private final String ACIERTO = "correcta";
    private MediaPlayer error;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fuentes );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        foto1 = findViewById( R.id.foto1 );
        foto2 = findViewById( R.id.foto2 );
        foto3 = findViewById( R.id.foto3 );
        foto4 = findViewById( R.id.foto4 );

        error = MediaPlayer.create( this, R.raw.error );
        error.seekTo( 500 );

        foto1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if( !foto1.getTag().toString().equals( ACIERTO ) ){
                    error.start();
                }else{
                    Intent finFuentes = new Intent( Fuentes.this , FinFuentes.class );
                    startActivity( finFuentes );
                    finish();
                }
            }
        } );

        foto2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if( !foto2.getTag().toString().equals( ACIERTO ) ){
                    error.start();
                }else{
                    Intent finFuentes = new Intent( Fuentes.this , FinFuentes.class );
                    startActivity( finFuentes );
                    finish();
                }
            }
        } );

        foto3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if( !foto3.getTag().toString().equals( ACIERTO ) ){
                    error.start();
                }else{
                    Intent finFuentes = new Intent( Fuentes.this , FinFuentes.class );
                    startActivity( finFuentes );
                    finish();
                }
            }
        } );

        foto4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if( !foto4.getTag().toString().equals( ACIERTO ) ){
                    error.start();
                }else{
                    saveChanges();
                    Intent finFuentes = new Intent( Fuentes.this , FinFuentes.class );
                    startActivity( finFuentes );
                    finish();
                }
            }
        } );
    }

    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.DORRETXEA ) + "2" ;
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
