package com.dev.mohawk.laudioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.mapResources.Places;

public class TrenGeltokia2 extends AppCompatActivity {

    private TextView tv,continuar;
    private ImageView iv,btnjarraitu;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren_geltokia2);

        tv = findViewById(R.id.textoBocata);
        iv = findViewById(R.id.fototren);
        continuar = findViewById(R.id.jarraituTren);
        btnjarraitu = findViewById(R.id.jarraitu);

        new Handler().postDelayed(new Runnable() {

            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void run() {
                // CAMBIAMOS EL BOCADILLO POR LA FOTO DEL TREN Y BORRAMOS EL TEXTO
                tv.setText("");
                iv.setImageDrawable(getDrawable(R.drawable.trengeltokia));
                btnjarraitu.setVisibility(View.VISIBLE);
                continuar.setText(R.string.jarraitu);
            }

        },4000);

        btnjarraitu.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onTouch(View v, MotionEvent event) {
                int accion = event.getAction();
                switch (accion){
                    case MotionEvent.ACTION_DOWN:
                        btnjarraitu.setImageDrawable(getDrawable(R.drawable.ic_btn2hover));
                        break;
                    case MotionEvent.ACTION_UP:
                        btnjarraitu.setImageDrawable(getDrawable(R.drawable.ic_btn2));
                        continuar(v);
                        break;
                }
                return true;
            }
        });
    }

    public void continuar(View view){

        Intent intento = new Intent(getApplicationContext(),NavegacionActivity.class);
        intento.putExtra(NavegacionActivity.DESTINO, Places.getId(Places.TREN));
        intento.putExtra(NavegacionActivity.ACTIVIDAD,NavegacionActivity.TRENGL3);
        startActivity(intento);
        finish();
    }
}
