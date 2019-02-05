package com.dev.mohawk.laudioapp;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.mohawk.laudioapp.mapResources.Places;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class NavegacionActivity extends AppCompatActivity {

    public static final String DESTINO = "nav_dst";

    private MapView mapa;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Mapbox.getInstance( this,getString( R.string.mapbox_key ) );
        setContentView( R.layout.activity_navegacion );
        mapa = findViewById( R.id.n_map );
        mapa.onCreate( savedInstanceState );
//        guardaremos el id del destino
        final int destino;
        destino = getIntent().getExtras().getInt( DESTINO );
        mapa.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull MapboxMap mapboxMap ) {
                mapboxMap.getUiSettings().setLogoEnabled( false );
                mapboxMap.getUiSettings().setAttributionEnabled( false );

                CameraPosition def = new CameraPosition.Builder()
                        .target( Places.getPlace( destino ) )
                        .zoom( 13 )
                        .tilt( 30 )
                        .build();

                mapboxMap.setStyle( new Style.Builder().fromUrl( getString( R.string.style_url ) ) );
                mapboxMap.animateCamera( CameraUpdateFactory.newCameraPosition( def ), 400 );
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapa.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapa.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapa.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapa.onPause();
    }

    @Override
    public void onSaveInstanceState( Bundle outState, PersistableBundle outPersistentState ) {
        super.onSaveInstanceState( outState, outPersistentState );
        mapa.onSaveInstanceState( outState );
    }
}
