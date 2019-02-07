package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Zeramika2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeramika2);
    }

    private void saveChanges(){
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.ZERAMIKA ) + "2" ;
//        actualizamos la base de datos
        m.updateLastPoint( Integer.parseInt( id ) );
    }

    private void nextActivity(){
        saveChanges();
        Intent i = new Intent( this, Zeramika2Activity.class );
        startActivity( i );
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
    }
}
