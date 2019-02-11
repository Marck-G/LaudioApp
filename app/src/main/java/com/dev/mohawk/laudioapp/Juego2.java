package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Juego2 extends AppCompatActivity {

    private ImageView bot1_p2,bot2_p2,bot3_p2;
    private TextView textoJuego2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);

        bot1_p2=findViewById(R.id.bot1_p2);
        bot2_p2=findViewById(R.id.bot2_p2);
        bot3_p2=findViewById(R.id.bot3_p2);

       textoJuego2=findViewById(R.id.textoJuego2);



        bot1_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot1_p2.setImageResource(R.drawable.ic_btnwrong);
                textoJuego2.setText(getString(R.string.textoMal2));
                bot2_p2.setImageResource(R.drawable.ic_btn22);
                bot3_p2.setImageResource(R.drawable.ic_btn22);


            }
        });


        bot2_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot2_p2.setImageResource(R.drawable.ic_btncorrect);
                textoJuego2.setText(getString(R.string.textoBien2));
                bot1_p2.setImageResource(R.drawable.ic_btn22);
                bot3_p2.setImageResource(R.drawable.ic_btn22);

                //salto a la foto completa al acertar la pregunta
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intento=new Intent(Juego2.this,FinJuego2.class);
                        saveChanges();
                        startActivity(intento);
                        finish();
                    }
                },4000);


            }
        });

        bot3_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot3_p2.setImageResource(R.drawable.ic_btnwrong);
                textoJuego2.setText(getString(R.string.textoMal2));
                bot1_p2.setImageResource(R.drawable.ic_btn22);
                bot2_p2.setImageResource(R.drawable.ic_btn22);


            }
        });

    }

    private void saveChanges(){
        Places.setContext(this);
//        creamos la instancia de la base de datos
        DBManager m = new DBManager( this, DBManager.DB_NAME, null, 1 );
//        construimos el id
        String id = Places.getId( Places.ELIZA ) + "1" ;
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
