package com.dev.mohawk.laudioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity {

    private ImageView botonhasi;
    private ImageView botonatera;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        botonhasi = findViewById(R.id.botonhasi);
        botonatera = findViewById(R.id.botonatera);

        /**
         * LOS DOS LISTENERS SIGUIENTES SON PARA CONTROLAR CUANDO PRESIONAN EL BOTON Y CUANDO LO SUELTAN
         * AL PRESIONAR LA IMAGEN CAMBIA PARA QUE PAREZCA UNA ANIMACION Y AL SOLTAR LA IMAGEN VUELVE A CAMBIAR
         * Y SE LLAMA AL METODO CORRESPONDIENTE
         */
        botonhasi.setOnTouchListener(new OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onTouch(View v, MotionEvent event) {
                int accion = event.getAction();
                switch (accion){
                    case MotionEvent.ACTION_DOWN:
                        botonhasi.setImageDrawable(getDrawable(R.drawable.ic_btn2hover));
                        break;
                    case MotionEvent.ACTION_UP:
                        botonhasi.setImageDrawable(getDrawable(R.drawable.ic_btn2));
                        lanzarMapa(v);
                        break;
                }
                return true;
            }
        });
        botonatera.setOnTouchListener(new OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int accion = event.getAction();
                switch (accion){
                    case MotionEvent.ACTION_DOWN:
                        botonatera.setImageDrawable(getDrawable(R.drawable.ic_btn2hover));
                        break;
                    case MotionEvent.ACTION_UP:
                        botonatera.setImageDrawable(getDrawable(R.drawable.ic_btn2));
                        cerrarApp(v);
                        break;
                }
                return true;
            }
        });
    }
    public void lanzarMapa (View view){
        // LANZAMOS EL MAPA
        Intent intento = new Intent(this,LevelChooser.class);
        startActivity(intento);
    }
    public void cerrarApp (View view){
        finish();
    }
}
