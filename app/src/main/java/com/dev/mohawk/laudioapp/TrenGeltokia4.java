package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TrenGeltokia4 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren_geltokia4);
    }

    public void continuar4(View view){
        Intent intento = new Intent(this,Zeramika1Activity.class);
        startActivity(intento);
        finish();
    }
}
