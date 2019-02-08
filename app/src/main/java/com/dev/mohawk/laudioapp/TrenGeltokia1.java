package com.dev.mohawk.laudioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class TrenGeltokia1 extends AppCompatActivity {

    private ImageView continuar;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren_geltokia1);

        continuar = findViewById(R.id.jarraitu);
        continuar.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onTouch(View v, MotionEvent event) {
                int accion = event.getAction();
                switch (accion){
                    case MotionEvent.ACTION_DOWN:
                        continuar.setImageDrawable(getDrawable(R.drawable.ic_btn2hover));
                        break;
                    case MotionEvent.ACTION_UP:
                        continuar.setImageDrawable(getDrawable(R.drawable.ic_btn2));
                        lanzarPuzzle(v);
                        break;
                }
                return true;
            }
        });

    }

    private void saveChanges(){
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.TREN ) + "1" ;
//        actualizamos la base de datos
        m.updateLastPoint( Integer.parseInt( id ) );
    }

    public void lanzarPuzzle(View view){
        saveChanges();
        Intent intent = new Intent( getApplicationContext(), PuzzleActivity.class );
        intent.putExtra( "assetName", "tren.jpg" );
        startActivity( intent );
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity( new Intent( this, MainActivity.class ) );
        finish();
    }
}
