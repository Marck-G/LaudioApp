package com.dev.mohawk.laudioapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class InicioPuzle extends AppCompatActivity {

    private Button botJarr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_puzle);


       /*botJarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(InicioPuzle.this,PuzzleActivity.class);
                startActivity(intento);
            }
        });*/
    }
}
