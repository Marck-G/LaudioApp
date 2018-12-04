package com.dev.mohawk.laudioapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LevelChooser extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_chooser);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // MARCADORES PARA EL MAPA
        LatLng laudio = new LatLng(42.846718, -2.671635);
        LatLng ikastola = new LatLng(43.9333, 3.66166);
        LatLng petriEliza = new LatLng(43.721666, 2.9623055);
        LatLng trengeltokia = new LatLng(43.1426388, 2.9606111111);
        LatLng stCruz = new LatLng(43.133027777777777777777777777778, 2.9699444444444444444444444444444);
        LatLng zeramika = new LatLng(43.133555555555555555555555555556, 2.9692777777777777777777777777778);
        LatLng jauregia = new LatLng(43.1335, 2.9708055555555555555555555555556);
        LatLng parkea = new LatLng(43.145861111111111111111111111111, 2.9678611111111111111111111111111);
        LatLng dorretxea = new LatLng(43.147222222222222222222222222222, 2.9691666666666666666666666666667);

        mMap.addMarker(new MarkerOptions().position(ikastola).title("Laudioko Ikastola"));
        mMap.addMarker(new MarkerOptions().position(petriEliza).title("Lamuzako Done Petri Eliza"));
        mMap.addMarker(new MarkerOptions().position(trengeltokia).title("Laudioko tren geltokia"));
        mMap.addMarker(new MarkerOptions().position(stCruz).title("Santa Cruz de Llodio"));
        mMap.addMarker(new MarkerOptions().position(zeramika).title("Zeramikako lantegia"));
        mMap.addMarker(new MarkerOptions().position(jauregia).title("Katuxa jauregia"));
        mMap.addMarker(new MarkerOptions().position(parkea).title("Lamuzako parkea"));
        mMap.addMarker(new MarkerOptions().position(dorretxea).title("Ugarteko Dorretxea "));

        // ESTO INDICA DONDE COMIENZA LA CAMARA
        mMap.moveCamera(CameraUpdateFactory.newLatLng(laudio));
    }
}
