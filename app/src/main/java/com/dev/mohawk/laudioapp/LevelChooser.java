package com.dev.mohawk.laudioapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LevelChooser extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker markIkastola,markPetriEliza,markTren,markCruz,markLantegia,markJauregia,markParkea,markDorretxea;
    private static LatLng laudio = new LatLng(43.140779, -2.966176);;
    private ImageView botonhasi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_chooser);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        botonhasi = findViewById(R.id.botonhasi);
    }

    /**
     * Permite manipular el mapa una vez esta listo para ser usado.
     * Aqui es donde podemos poner marcadores, listeners y mover la camara
     * Si los servicios de google play no estan instalados el ususario será dirigido para que los instale
     * El metodo solo estara disponible y solo sera llamado una vez los servicios
     * de google play esten instalados y se vuelva a iniciar la app.
     */

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // MARCADORES PARA EL MAPA
        LatLng ikastola = new LatLng(43.146666666666666666666666666667, -2.9618611111111111111111111111111);
        LatLng petriEliza = new LatLng(43.143138888888888888888888888889, -2.9623055555555555555555555555556);
        LatLng trengeltokia = new LatLng(43.1426388, -2.9606111111);
        LatLng stCruz = new LatLng(43.133027777777777777777777777778, -2.9699444444444444444444444444444);
        LatLng zeramika = new LatLng(43.133555555555555555555555555556, -2.9692777777777777777777777777778);
        LatLng jauregia = new LatLng(43.1335, -2.9708055555555555555555555555556);
        LatLng parkea = new LatLng(43.145861111111111111111111111111, -2.9678611111111111111111111111111);
        LatLng dorretxea = new LatLng(43.147222222222222222222222222222, -2.9691666666666666666666666666667);

        /**
         * EL LISTENER PARA LOS MARKER ES EL SIGUIENTE:
         * mMap.OnMarkerClickListener
         *
         * CAMBIAR EL COLOR DE UN MARCADOR:
         * public static BitmapDescriptor defaultMarker (float hue)
         * Donde hue debe tener por preferencia un valor de color establecido en las siguientes constantes:
         * float HUE_AZURE
         * float HUE_BLUE
         * float HUE_CYAN
         * float HUE_GREEN
         * float HUE_MAGENTA
         * float HUE_ORANGE
         * float HUE_RED
         * float HUE_ROSE
         * float HUE_VIOLET
         * float HUE_YELLOW
         *
         *
         * position utilzia una variable de tipo LatLgn (las creadas más arriba)
         * title lo ponemos a null para que no salga cuando hacemos clic sobre el marcador
         * icon, usamos el icono por defecto pero cambiado de color
         */

        // MARCADOR LAUDIOKO IKASTOLA
        MarkerOptions mIkastola = new MarkerOptions()
                .position(ikastola)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markIkastola = mMap.addMarker(mIkastola);

        // MARCADOR PETRI ELIZA
        MarkerOptions mPetriEliza = new MarkerOptions()
                .position(petriEliza)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markPetriEliza = mMap.addMarker(mPetriEliza);

        // MARCADOR TREN GELTOKIA
        MarkerOptions mTrenGeltokia = new MarkerOptions()
                .position(trengeltokia)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markTren = mMap.addMarker(mTrenGeltokia);

        // MARCADOR SANTA CRUZ
        MarkerOptions mStCruz = new MarkerOptions()
                .position(stCruz)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markCruz = mMap.addMarker(mStCruz);

        // MARCADOR ZERAMIKA LANTEGIA
        MarkerOptions mZeramika = new MarkerOptions()
                .position(zeramika)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markLantegia = mMap.addMarker(mZeramika);

        // MARCADOR JAUREGIA
        MarkerOptions mJauregia = new MarkerOptions()
                .position(jauregia)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markJauregia = mMap.addMarker(mJauregia);

        // MARCADOR LAMUZAKO PARKEA
        MarkerOptions mParkea = new MarkerOptions()
                .position(parkea)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markParkea = mMap.addMarker(mParkea);

        // MARCADOR UGARTEKO DORRETXEA
        MarkerOptions mDorretxea = new MarkerOptions()
                .position(dorretxea)
                .title(null)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markDorretxea = mMap.addMarker(mDorretxea);

        // ESTO INDICA DONDE COMIENZA LA CAMARA
        mMap.moveCamera(CameraUpdateFactory.newLatLng(laudio));
        // CON EL MIN ZOOM INDICAMOS CON QUE ZOOM EMPIEZA LA CAMARA
        mMap.setMinZoomPreference(15.2f);
        // AL PONER EL MAX ZOOM AL MISMO VALOR QUE EL MIN ZOOM IMPEDIMOS QUE SE PUEDA METER ZOOM
        mMap.setMaxZoomPreference(15.2f);

        // LA SIGUIENTE LINEA DESHABILITA EL MOVIMIENTO DE LA CAMARA ASI NO PODRAN MOVERSE DE LAUDIO
        mMap.getUiSettings().setAllGesturesEnabled(false);

        mMap.setOnMarkerClickListener(this);
    }

    public void atras(View view){
        finish();
    }

    // EVENTO onCLICK PARA LOS MARCADORES
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markPetriEliza)){
            Intent intento = new Intent(this, Conver_inicial.class);
            startActivity(intento);
        }
        if ( marker.equals( markTren ) ){
            Intent intent = new Intent( getApplicationContext(), PuzzleActivity.class );
            intent.putExtra( "assetName", "tren.jpg" );
            startActivity( intent );
        }
        return true;
    }
}
