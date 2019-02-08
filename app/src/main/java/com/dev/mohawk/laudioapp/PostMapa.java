package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PostMapa extends AppCompatActivity {

    private ImageView botJ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_mapa);


        botJ=findViewById(R.id.botJ);

        //salto al siguiente juego (pregunta sobre la puerta de la iglesia)
        botJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(PostMapa.this,Juego2.class);
                startActivity(intento);
            }
        });

    }
}
