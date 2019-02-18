package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MapFragment extends Fragment {
    private ImageView imgTxomin;

    private Button botonTren;
    private Lorategia lr;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.activity_map_fragment, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        imgTxomin = ( ImageView ) view.findViewById( R.id.imgTxomin );
        imgTxomin.setVisibility( View.INVISIBLE );



        botonTren= ( Button ) view.findViewById( R.id.botTren );

        botonTren.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Lorategia.getLor().saveChanges();
                Intent horario = new Intent( getContext() , HorarioActivity.class );
                horario.putExtra( HorarioActivity.DIRECCION, HorarioActivity.ST_LLODIO );
                horario.putExtra( HorarioActivity.SIG_ACT,"" );
                startActivity( horario );
                Lorategia.getLor().finish();
            }
        } );


    }
}
