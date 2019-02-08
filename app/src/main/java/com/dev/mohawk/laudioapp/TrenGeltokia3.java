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

public class TrenGeltokia3 extends AppCompatActivity {


    private ImageView aukera1,aukera2,aukera3,persona,jarraitu;
    private TextView txomin,continuar;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren_geltokia3);

        aukera1 = findViewById(R.id.opcion1);
        aukera2 = findViewById(R.id.opcion2);
        aukera3 = findViewById(R.id.opcion3);
        persona = findViewById(R.id.persona);
        txomin = findViewById(R.id.preguntaTren);
        jarraitu = findViewById(R.id.jarraitu);
        continuar = findViewById(R.id.jarraituText);

    }
    public void elegirBien(View view){

        aukera1.setImageResource(R.drawable.ic_btncorrect);
        txomin.setText(R.string.TrenGeltokia3_zuzena);
        persona.setImageResource(R.drawable.txomin);
        new Handler().postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void run() {
                jarraitu.setVisibility(View.VISIBLE);
                continuar.setVisibility(View.VISIBLE);
                aukera1.setImageResource(R.drawable.ic_btn22);
            }
        },3000);
    }

    public void elegirMal(View view){

        aukera1.setImageResource(R.drawable.ic_btnwrong);
        txomin.setText(R.string.TrenGeltokia3_okerra);
        persona.setImageResource(R.drawable.txomin);
        new Handler().postDelayed(new Runnable() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void run() {
                aukera1.setImageResource(R.drawable.ic_btn22);
            }
        },4000);
    }

    public void continuar(View view){
        Intent intento = new Intent(this,TrenGeltokia4.class);
        startActivity(intento);
        finish();
    }
}
