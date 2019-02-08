package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class FinJuego2 extends AppCompatActivity {

    private ImageView botJar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_juego2);

        botJar=findViewById(R.id.botJar);

        botJar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(FinJuego2.this,InicioPuzle.class);
                startActivity(intento);
            }
        });




    }
}
