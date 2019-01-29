package com.dev.mohawk.laudioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.horarios.HorariosListAdapter;
import com.marck.renfeApi.contentSelector.Selector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.dev.mohawk.laudioapp.MainActivity.LLODIO_ST_H_FILE;

public class HorarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_horario );
        try {
            Selector s = Selector.build().setDocument( new BufferedReader( new FileReader( getFileStreamPath( LLODIO_ST_H_FILE ) ) ) );
            String[][] data = s.getTabla();
            String info = String.format( getString( R.string.h_last_update ), s.getDate() );
            HorariosListAdapter ad = new HorariosListAdapter( this, R.id.h_lista, data );
            ( ( ListView )findViewById( R.id.h_lista ) ).setAdapter( ad );
            ( ( TextView ) findViewById( R.id.h_info ) ).setText( s.getOrigen() + " - " + s.getDestino() );
            ( ( TextView ) findViewById( R.id.h_date ) ).setText( info );
        } catch ( Selector.EmptyDocumentException e ) {
            e.printStackTrace();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
