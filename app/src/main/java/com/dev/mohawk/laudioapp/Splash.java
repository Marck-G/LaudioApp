package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.MainActivity;
import com.dev.mohawk.laudioapp.R;

public class Splash extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        establecemos la vista a fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        iniciamos la actividad después de un delay
        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent intento=new Intent(Splash.this,MainActivity.class);
                startActivity(intento);
//                cerramos la actividad actual para evitar sobrecargar la ram
                finish();
            }
        },3000);
    }
}
