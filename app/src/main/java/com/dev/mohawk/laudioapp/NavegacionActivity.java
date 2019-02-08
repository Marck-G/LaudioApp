package com.dev.mohawk.laudioapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.dev.mohawk.laudioapp.mapResources.Places;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.OnLocationStaleListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;

public class NavegacionActivity extends AppCompatActivity {

    public static final String DESTINO = "nav_dst";
    public static final String ACTIVIDAD = "act";
    public static final int ACTV2 =  0x0002;
    public static final int TRENGL3 = 0x003;


    private MapView mapa;
    private int actividadSiguiente;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Mapbox.getInstance( this, getString( R.string.mapbox_key ) );
        setContentView( R.layout.activity_navegacion );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mapa = findViewById( R.id.n_map );
        mapa.onCreate( savedInstanceState );
        actividadSiguiente = getIntent().getExtras().getInt(ACTIVIDAD);
//        guardaremos el id del destino
        final int destino;
        destino = getIntent().getExtras().getInt( DESTINO );
        mapa.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull final MapboxMap mapboxMap ) {
                mapboxMap.getUiSettings().setLogoEnabled( false );
                mapboxMap.getUiSettings().setAttributionEnabled( false );

                CameraPosition def = new CameraPosition.Builder()
                        .target( Places.getPlace( destino ) )
                        .zoom( 18 )
                        .tilt( 58 )
                        .build();
                mapboxMap.setStyle( new Style.Builder().fromUrl( getString( R.string.style_url ) ),
                        new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded( @NonNull Style style ) {
                               setLocation( mapboxMap, style );
                            }
                        } );
                mapboxMap.animateCamera( CameraUpdateFactory.newCameraPosition( def ), 400 );
                mapboxMap.addMarker( new MarkerOptions().position( Places.getPlace( destino ) ) );

            }
            private void setLocation( final MapboxMap mapboxMap, Style s ){
                if ( ActivityCompat.checkSelfPermission( NavegacionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( NavegacionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    new PermissionsManager( new PermissionsListener() {
                        @Override
                        public void onExplanationNeeded( List< String > permissionsToExplain ) {

                        }

                        @Override
                        public void onPermissionResult( boolean granted ) {
                            if ( !granted ){
//                                si no hay pormisos mostramos el mensaje y pasamos a la siguiente actividad
                                Toast.makeText( NavegacionActivity.this, "No se puede acceder a la navegacion", Toast.LENGTH_SHORT );
                                starActivity();
                            }
                        }
                    } ).requestLocationPermissions( NavegacionActivity.this );
                    return;
                }
                mapboxMap.getLocationComponent().activateLocationComponent( NavegacionActivity.this, s );
                mapboxMap.getLocationComponent().zoomWhileTracking( 20 );
                mapboxMap.getLocationComponent().setLocationComponentEnabled( true );
                mapboxMap.getLocationComponent().setCameraMode( CameraMode.TRACKING );
                mapboxMap.getLocationComponent().setRenderMode( RenderMode.COMPASS );
                mapboxMap.getLocationComponent().addOnLocationStaleListener( new OnLocationStaleListener() {
                    @Override
                    public void onStaleStateChange( boolean isStale ) {
                        if( isStale ){
                            @SuppressLint( "MissingPermission" )
                            LatLng pos = new LatLng( mapboxMap.getLocationComponent().getLastKnownLocation().getLatitude(),
                                    mapboxMap.getLocationComponent().getLastKnownLocation().getLongitude() );
                            if ( pos.equals( Places.getPlace( destino ) ) ){

                                starActivity();
                            }
                        }
                    }
                } );
            }
        } );


    }

    public void starActivity(){
        Intent i = null;
        switch (actividadSiguiente){
            case TRENGL3:
                i = new Intent(this,TrenGeltokia3.class);
                break;
        }
        startActivity(i);
        finish();


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
