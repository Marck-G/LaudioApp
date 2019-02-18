package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Gudariak extends AppCompatActivity {


    private ImageView gudari;
    private LinearLayout gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gudariak);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gps = findViewById( R.id.lMapa );
        gps.setVisibility( View.INVISIBLE );

        gudari = findViewById( R.id.gudariak );
        gudari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gudari.setVisibility( View.INVISIBLE );
                gps.setVisibility( View.VISIBLE );

            }
        });


    }

    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.DORRETXEA ) + "7" ;
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
