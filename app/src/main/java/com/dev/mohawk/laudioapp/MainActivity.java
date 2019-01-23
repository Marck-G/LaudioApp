package com.dev.mohawk.laudioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.mapbox.mapboxsdk.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private static final int MAP_ANIMATION_TIME = 700;
//    vista del mapa
    private MapView mapView;

//    Botones de control:
//      los botones se componen de una imagen y un texto, en lugar de coger las dos partes
//      se han introducido en un layout y se trabaja directamente con el layout como un único
//      elemento, así se facilita el trabajo.
    private ConstraintLayout    btn_hasi; // boton de inicio
    private ConstraintLayout    btn_atera; // boton de salida
    private ConstraintLayout    btn_reiniciar; // boton para borrar los datos y empezar desde 0
    private ConstraintLayout    btn_continuar; // para seguir desde la ultima actividad guardada
    private ImageView           btn_back; // boton para salir de la vista onePoint
//    Titulo superior
    private TextView title;
//    Hub de vista de un único punto, layout con los botones para la vista de un punto
    private ConstraintLayout onePointHub;



    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
//        establecemos la vista a fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


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
