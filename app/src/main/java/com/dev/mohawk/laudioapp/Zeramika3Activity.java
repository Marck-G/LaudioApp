package com.dev.mohawk.laudioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Zeramika3Activity extends AppCompatActivity {

    private TextView txomin;
    private String[] texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeramika3);
//        flag para fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txomin = findViewById( R.id.txomin13 );
        texts = getString( R.string.txomin13 ).split( ";" );
        txomin.setText( texts[0] );
    }
}
