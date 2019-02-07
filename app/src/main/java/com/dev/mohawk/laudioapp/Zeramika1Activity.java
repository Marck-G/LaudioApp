package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Zeramika1Activity extends AppCompatActivity {
    private ConstraintLayout imgs;
    private ConstraintLayout bocadillo;
    private TextView dialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeramika1);
        imgs = findViewById( R.id.zeramika1_img );
        bocadillo = findViewById( R.id.zeramika1_bocadillo );
        imgs.setAlpha(0);
        dialogo = findViewById( R.id.zeramika1_dialog);
        dialogo.setText( getString( R.string.arantza14_1).split(";")[0] );
        showImg();
    }

    private void showImg(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgs.animate().alpha( 100 ).setDuration(700).start();
                dialogo.setText( getString( R.string.arantza14_1 ).split(";")[1] );
                showPista();
            }
        }, 6000);
    }

    private void showPista() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                escondemos el bocadillo del dialogo
                bocadillo.setVisibility(View.INVISIBLE);
//                animamos a Arantza
                findViewById(R.id.arantza_14).animate()
                        .translationXBy(300)
                        .setDuration(600)
                        .start();
                findViewById(R.id.zeramika1_pista).setVisibility(View.VISIBLE);
//                escondemos a Arantza despues de la animacion
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById( R.id.arantza_14 ).setVisibility( View.INVISIBLE );
                        showTrena();
                    }
                }, 1000);
            }
        }, 4000);
    }

    private void showTrena(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.zeramika1_trena).setVisibility(View.VISIBLE);
                nextActHandler();
            }
        }, 10000);

    }
    private void nextActHandler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 3000 );
    }
    private void saveChanges(){
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.ZERAMIKA ) + "1" ;
//        actualizamos la base de datos
        m.updateLastPoint( Integer.parseInt( id ) );
    }

    private void nextActivity(){
        saveChanges();
        Intent i = new Intent( this, HorarioActivity.class );
        i.putExtra( HorarioActivity.DIRECCION, HorarioActivity.LLODIO_ST );
        i.putExtra( HorarioActivity.ACTIVIDAD_SIGUIENTE, HorarioActivity.ZERAMIKA_2 );
        startActivity( i );
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
        finish();
    }
}
