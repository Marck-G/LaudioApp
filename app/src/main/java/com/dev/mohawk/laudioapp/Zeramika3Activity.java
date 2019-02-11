package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Zeramika3Activity extends AppCompatActivity {

    private TextView txomin;
    private String[] texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeramika3);
//        flag para fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txomin = findViewById( R.id.txomin13 );
        texts = getString( R.string.txomin13 ).split( ";" );
        txomin.setText( texts[0] );
    }

    private void nextDialog(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txomin.setText( texts[1] );
                nextDialog1();
            }
        }, 3000);
    }

    private void nextDialog1(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txomin.setText( texts[2] );
                nextAct();
            }
        }, 3000);
    }

    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.ZERAMIKA ) + "3" ;
//        actualizamos la base de datos
        m.updateLastPoint( Integer.parseInt( id ) );
    }

    private void nextAct(){
        saveChanges();
        Intent i = new Intent( );
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
        finish();
    }
}
