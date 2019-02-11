package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mohawk.laudioapp.horarios.HorariosListAdapter;
import com.marck.renfeApi.contentSelector.Selector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.dev.mohawk.laudioapp.MainActivity.LLODIO_ST_H_FILE;
import static com.dev.mohawk.laudioapp.MainActivity.ST_LLODIO_H_FILE;

public class HorarioActivity extends AppCompatActivity {
    public static final String DIRECCION = "DIRECCION";
    public static final int LLODIO_ST = 0x00f2;
    public static final int ST_LLODIO = 0x00f1;
    // TODO: crear una constante int por cada actividad que se necesite
    public static final String SIG_ACT = "act_sig";
    public static final int ZERAMIKA2 = 0x000f2;
    private String[][] data;
    private Selector s;
    private int sgAct;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_horario );
        Bundle b = getIntent().getExtras();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int chose = b.getInt( DIRECCION );
        String file = ST_LLODIO_H_FILE;
//        recogemos la actividad siguiente
        sgAct = b.getInt( SIG_ACT );
        if( chose == LLODIO_ST )
            file = LLODIO_ST_H_FILE;
        try {
            s = Selector.build().setDocument( new BufferedReader( new FileReader( getFileStreamPath( file ) ) ) );

            String info = String.format( getString( R.string.h_last_update ), s.getDate() );
            data = null;
            ( ( TextView ) findViewById( R.id.h_info ) ).setText( s.getOrigen() + " - " + s.getDestino() );
            ( ( TextView ) findViewById( R.id.h_date ) ).setText( info );
            Toast.makeText( getApplicationContext(), "Leyendo los horarios", Toast.LENGTH_LONG ).show();
            new Handler().postDelayed(  new Runnable() {
                @Override
                public void run() {
                    try {
                        data = s.getTabla();
                        HorariosListAdapter ad = new HorariosListAdapter( HorarioActivity.this, R.id.h_lista, data );
                        ( ( ListView )findViewById( R.id.h_lista ) ).setAdapter( ad );
                    } catch ( Selector.EmptyDocumentException e ) {
                        Log.e( "Horarios", "Error de lectura", e );
                    }
                }
            } , 300 );



        } catch ( FileNotFoundException e ) {
           Toast.makeText( getApplicationContext(), "No se han encontrado los horario", Toast.LENGTH_SHORT ).show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    // TODO: crear un case por cada constante
    private void startAct(){
        Intent i = null;
        switch ( sgAct ){
            case ZERAMIKA2:
                i = new Intent( this, Zeramika3Activity.class );
                break;
        }
        startActivity(i);
        finish();
    }

    public void continuar(View view) {
        startAct();
    }
}
