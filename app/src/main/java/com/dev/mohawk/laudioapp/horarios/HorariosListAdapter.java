package com.dev.mohawk.laudioapp.horarios;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.mohawk.laudioapp.R;

import java.util.zip.Inflater;


public class HorariosListAdapter extends ArrayAdapter<String[]> {

    private  String[][] data;
    public HorariosListAdapter( @NonNull Context context, int resource, @NonNull String[][] objects ) {
        super( context, resource, objects );
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {
        LayoutInflater inflater = LayoutInflater.from( getContext() );
        View v = inflater.inflate( R.layout.horario_item, null );
        TextView linea = v.findViewById( R.id.h_linea );
        linea.setText( data[position][0] );
        TextView hora = v.findViewById( R.id.h_hora_salida );
        hora.setText( data[position][1].replace( ".", ":" ) );
        return v;
    }
}
