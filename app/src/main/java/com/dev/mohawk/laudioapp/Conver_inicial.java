package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.R;
import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Conver_inicial extends AppCompatActivity {

    private ImageView bot1,bot2,bot3;
    private TextView text1,text2,text3,textBocata;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver_inicial);

        bot1=findViewById(R.id.bot1);
        bot2=findViewById(R.id.bot2);
        bot3=findViewById(R.id.bot3);

        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        textBocata=findViewById(R.id.textBocata);

     bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot1.setImageResource(R.drawable.ic_btnwrong);
                textBocata.setText(getString(R.string.textoMal));
                bot2.setImageResource(R.drawable.ic_btn22);
                bot3.setImageResource(R.drawable.ic_btn22);
            }
        });

        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot2.setImageResource(R.drawable.ic_btnwrong);
                textBocata.setText(getString(R.string.textoMal));
                bot1.setImageResource(R.drawable.ic_btn22);
                bot3.setImageResource(R.drawable.ic_btn22);

            }
        });

        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bot3.setImageResource(R.drawable.ic_btncorrect);
                textBocata.setText(getString(R.string.textoBien));
                bot2.setImageResource(R.drawable.ic_btn22);
                bot1.setImageResource(R.drawable.ic_btn22);

                //salto a la foto completa al acertar la pregunta
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       Intent intento=new Intent(Conver_inicial.this, NavegacionActivity.class);
                       intento.putExtra( NavegacionActivity.DESTINO, Places.getId( Places.ELIZA ));
                       intento.putExtra( NavegacionActivity.ACTIVIDAD, NavegacionActivity.JUEGO2 );
                       startActivity(intento);
                       finish();
                        DBManager manager = new DBManager( Conver_inicial.this , "activities", null, 1 );
                        manager.updateLastPoint( 10 );
                    }
                },3500);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
//        POR TEMAS DE MEMORIA SIEMPRE CERRAMOS LA ACTIVIDAD ACTUAL DESPUES DE LLAMAR A LA
//        SIGUIENTE ACTIVIDAD
        finish();
    }
}
