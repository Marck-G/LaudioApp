package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.mohawk.laudioapp.R;
import com.dev.mohawk.laudioapp.mapResources.Places;

public class Foto_completa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_completa);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, TrenGeltokia1.class );
        startActivity( i );
        finish();
    }
}
