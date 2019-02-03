package com.dev.mohawk.laudioapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mohawk.laudioapp.database.DBManager;
import com.dev.mohawk.laudioapp.mapResources.Places;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import com.marck.renfeApi.RenfeRequest;
import com.marck.renfeApi.params.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.Main;

public class MainActivity extends AppCompatActivity {
//    nobre archivos
    public static final String LLODIO_ST_H_FILE = "llodio_st.h";
    public static final String ST_LLODIO_H_FILE = "st_llodio.h";
    public static final String MAP_OFFLINE_FILE = "mbgl-offline.db";
//    animacion
    private static final int MAP_ANIMATION_TIME = 700;
//    vista del mapa
    private MapView mapView;

//    Botones de control:
//      los botones se componen de una imagen y un texto, en lugar de coger las dos partes
//      se han introducido en un layout y se trabaja directamente con el layout como un único
//      elemento, así se facilita el trabajo.
    private ConstraintLayout    btn_hasi; // boton de inicio
    private ConstraintLayout    btn_atera; // boton de salida
    private ConstraintLayout    btn_reiniciar; // boton para borrar los datos y empezar desde 0
    private ConstraintLayout    btn_continuar; // para seguir desde la ultima actividad guardada
    private ImageView           btn_back; // boton para salir de la vista onePoint
//    barra de progreso para la descarga del mapa
    private ProgressBar mapLoad;

//    Titulo superior
    private TextView title;
//    Hub de vista de un único punto, layout con los botones para la vista de un punto
    private ConstraintLayout onePointHub;
//    controlamos en que tipo de vista estamos, en la de un solo punto o la general
    private boolean isPointView = false;
//    objeto base de datos
    private DBManager manager;





    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Mapbox.getInstance( this,getString( R.string.mapbox_key ) );
        setContentView( R.layout.activity_main );
//        establecemos la vista a fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        nos permite usar peticiones web sin estar en otro hilo
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        getSupportActionBar().hide();
//        colocamos la api key
//        recuperamos el elemento
        mapView = findViewById(R.id.mapView);
//        lo iniciamos
        mapView.onCreate(savedInstanceState);
//        iniciamos el mapa con los marcadores
        mapView.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull MapboxMap mapboxMap ) {
//                establecemos el estilo del mapa
                mapboxMap.setStyle(
                        new Style.Builder()
                                .fromUrl( getString( R.string.style_url ) )
                );
//                desabilitamos todos los gestos del mapa:
                mapboxMap.getUiSettings().setAllGesturesEnabled( false );
//                escondemos el logo
                mapboxMap.getUiSettings().setLogoEnabled( false );
//                escondemos la brujula
                mapboxMap.getUiSettings().setCompassEnabled( true );
//                escondemos la informacion de la api
                mapboxMap.getUiSettings().setAttributionEnabled( false );

            }
        } );
//        creamos la conexion a la base de datos
        manager = new DBManager( this, "activities", null, 1 );
//        establecemos el contexto a la clase Places para poder trabajar sobre el archivo strings
        Places.setContext( this );
//        recuperamos los elementos
        title = findViewById( R.id.main_title );
        btn_hasi = findViewById( R.id.btn_jaraitu );
        btn_atera = findViewById( R.id.btn_atera );
        btn_back = findViewById( R.id.btn_back );
        btn_continuar = findViewById( R.id.btn_continuar );
        btn_reiniciar = findViewById( R.id.btn_reiniciar );
        mapLoad = findViewById( R.id.mapDownload );
//        caracteristicas de la barra de progreso
        mapLoad.setMax( 100 );
        onePointHub = findViewById( R.id.point_view_controllers );
//        escondemos el hub de un punto
        onePointHub.setVisibility( View.INVISIBLE );
//        colocamos los marcadores
        setMarkers();
//        restablecemos la camara
        restoreCamera();
//        establecemos los eventos
        setEvents();
//        descarga de los datos
        descargarDatos();
    }


//    descargamos los datos que hagan falta solo si hay conexion y es necesarios
    private void descargarDatos(){
        ConnectivityManager manager = ( ConnectivityManager ) getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo inf = manager.getActiveNetworkInfo();
        File offlineMap = getFileStreamPath( MAP_OFFLINE_FILE );
        if( inf != null && inf.isConnected() ){
//            si no existe la base de datos, descargamos el mapa
            if( !offlineMap.exists() )
                new Handler().postDelayed( new Runnable() {
                @Override
                public void run() {
                    descargarMapa();
                }
            }, 10 );
            else
                mapLoad.setVisibility(View.INVISIBLE);
            try {
                descargaDeHorarios();
            } catch ( RenfeRequest.WrongDateFormatException e ) {
                Log.e( "HORARIOS", "EROOR", e );
            }
        } else{
            mapLoad.setVisibility(View.INVISIBLE);
        }
    }

//    establecemos todos los eventos
    private void setEvents(){
//        evento para el boton iniciar
        btn_hasi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                viewPoint( manager.getLastPoint() );
            }
        } );

//        salida de la app
        btn_atera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                onBackPressed();
            }
        } );
//        boton para volver a la vista general
        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                onBackPressed();
            }
        } );

//        evento de reinicio de base de datos
        btn_reiniciar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                manager.restartData();
                setMarkers();
                Intent i = new Intent( MainActivity.this, Conver_inicial.class );
                startActivity( i );

//                POR TEMAS DE MEMORIA SIEMPRE CERRAMOS LA ACTIVIDAD ACTUAL DESPUES DE LLAMAR A LA
//                SIGUIENTE ACTIVIDAD
                finish();
            }
        } );

        btn_continuar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                continuar();
//                finish();
            }
        } );
    }

//    creamos lo marcadores segun la informacion recuperada de la base de datos
    private void setMarkers(){
//        recuperamos el id del ultimo punto
        final int lastId = Places.getId( manager.getLastPoint() );
//        cogemos el numero total de marcadores
        final int total = Places.COUNT;
       mapView.getMapAsync( new OnMapReadyCallback() {
           @Override
           public void onMapReady( @NonNull MapboxMap mapboxMap ) {
               mapboxMap.clear();
               IconFactory factory = IconFactory.getInstance(  MainActivity.this );
//               colocamos los marcadores de los que ya estan hechos
               for ( int i = 1; i < lastId; i++ ){
                    mapboxMap.addMarker(
                            new MarkerOptions()
                            .position( Places.getPlace( i ) ) // establecemos el lugar
//                            le colocamos el icono del marcador
                            .setIcon( factory.fromResource( R.mipmap.ic_marker_v ) )
                    );
               }
//               marcaadores de los que faltan por hacer
               for ( int i = lastId; i <= total; i++ ){
                   mapboxMap.addMarker(
                           new MarkerOptions()
                                   .position( Places.getPlace( i ) ) // establecemos el lugar
//                            le colocamos el icono del marcador
                                   .setIcon( factory.fromResource( R.mipmap.ic_marker_x ) )
                   );
               }
           }
       } );
    }

//    continuamos desde el último punto guardado
    private void continuar(){
        int idLastAct = manager.getLastActivity();
        // TODO: switch con el id y por cada, un intent
        Intent intent = null;
        switch ( idLastAct ){
            case 10:
                intent = new Intent( this, Conver_inicial.class );
            case 11:
                intent = new Intent( this, Foto_completa.class );
        }
        if ( intent != null ){
            startActivity( intent );
            finish();
        }
    }


//    Devuelve la vista del mapa al la vista principal
    private void restoreCamera(){
//        establecemos el titulo
        title.setText( "Llodio" );
        setOnePointView( false );
//        recuperamos el mapa de mapbox
        mapView.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull MapboxMap mapboxMap ) {
//                establecemos la nueva posicion de camara
                CameraPosition position = new CameraPosition.Builder()
                        .target( Places.LLODIO )
                        .zoom( 14 )
                        .tilt( 0 ) // inclinacion vertical
                        .bearing( 0 ) // rotacion sobre el eje z
                        .build();
//                establecemos una animacion a la camara con un tiempo determinado
                mapboxMap.animateCamera( CameraUpdateFactory.newCameraPosition( position ),
                        MAP_ANIMATION_TIME );
            }
        } );
    }

    /**
     * Se establece el tipo de vista, si es true se esconde el hub de la
     * vista general y se muestra el hub de un solo punto y viceversa
     * @param set boolean
     */
    private void setOnePointView( boolean set ){
        if ( set ){
//            escondemos la vista general
            btn_hasi.setVisibility( View.INVISIBLE );
            btn_atera.setVisibility( View.INVISIBLE );
//            retrasamos la aparicion de la otra vista
            new Handler().postDelayed( new Runnable() {
                @Override
                public void run() {
//                    hacemos visible el hub de un solo punto
                    onePointHub.setVisibility( View.VISIBLE );
//                    le colocamos una animacion
                    onePointHub.animate()
                            .setDuration( MAP_ANIMATION_TIME / 2 ) // duracion en milisegundos
                            .translationY( -10 ) // variamos el eje y
                            .start(); // iniciamos la animacion
                }
            }, MAP_ANIMATION_TIME + 100 );

        } else {
//            escondemos la vista de un solo punto
            onePointHub.setVisibility( View.INVISIBLE );
//            retarasamos la aparicion de la vista general
            new Handler().postDelayed( new Runnable() {
                @Override
                public void run() {
                    btn_hasi.setVisibility( View.VISIBLE );
                    btn_atera.setVisibility( View.VISIBLE );

                }
            }, MAP_ANIMATION_TIME + 100 );

        }
//        actualizamos la variable local
        isPointView = set;
    }

    /**
     * Posiciona la c&aacute;mara sobre un punto en espec&iacute;fico del mapa, haciendo zomm
     * sobre &eacute;l.
     * @param pos LatLng posici&oacute;n en que se enfocar&aacute; la c&aacute;mara
     */
    private void viewPoint( final LatLng pos ){
        String name = Places.getName( pos );
        title.setText( name );
        setOnePointView( !isPointView );
        mapView.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull MapboxMap mapboxMap ) {
//                creamos la nueva camara
                CameraPosition position = new CameraPosition.Builder()
                        .target( pos ) // establecemos el punto a mostrar
                        .zoom( 19 )
                        .build();
//                colocamos la camara al mapa con una animacion
                mapboxMap.animateCamera( CameraUpdateFactory.newCameraPosition( position ),
                        MAP_ANIMATION_TIME );
            }
        } );
    }
//    descarga de horarios
    private void descargaDeHorarios() throws RenfeRequest.WrongDateFormatException {
        Date toDay = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "dd-MM-yyyy" );
//        creamos las variables
        final RequestParams st_llodio = new RequestParams()
                .put( RequestParams.HORA_DESTINO, "26" )
                .put( RequestParams.DESTINO, "13106" ) // santa cruz
                .put( RequestParams.ORIGEN, "13104" ) // llodio
                .put( RequestParams.HORA_ORIGEN, "2" )
                .put( RequestParams.FECHA, RenfeRequest.formatDate( format.format( toDay ) ) );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                RenfeRequest r = RenfeRequest.build();
                try {
                    r.setParams( st_llodio );
                    r.buildURL();
                    r.connect( MainActivity.this.openFileOutput( ST_LLODIO_H_FILE, MODE_PRIVATE ) );
                } catch ( RequestParams.ParamNotFoundException e ) {
                    Log.e( "HORARIOS", "Parametros erroneos", e );
                } catch ( FileNotFoundException e ) {
                    Log.e( "HORARIOS", "File not found", e );
                } catch ( MalformedURLException e ) {
                    Log.e( "HORARIOS", "URL erronea", e );
                } catch ( IOException e ) {
                    Log.e( "HORARIOS", "", e );
                }
            }
        },500 );

        final RequestParams llodio_st = new RequestParams()
                .put( RequestParams.HORA_DESTINO, "26" )
                .put( RequestParams.DESTINO, "13104" ) // santa cruz
                .put( RequestParams.ORIGEN, "13106" ) // llodio
                .put( RequestParams.HORA_ORIGEN, "2" )
                .put( RequestParams.FECHA, RenfeRequest.formatDate( format.format( toDay ) ) );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                RenfeRequest r = RenfeRequest.build();
                try {
                    r.setParams( llodio_st );
                    r.buildURL();
                    r.connect( MainActivity.this.openFileOutput( LLODIO_ST_H_FILE, MODE_PRIVATE ) );
                    Toast.makeText( MainActivity.this.getApplicationContext(),
                            "Horarios Actualizados", Toast.LENGTH_SHORT ).show();
                } catch ( RequestParams.ParamNotFoundException e ) {
                    Log.e( "HORARIOS", "Parametros erroneos", e );
                } catch ( FileNotFoundException e ) {
                    Log.e( "HORARIOS", "File not found", e );
                } catch ( MalformedURLException e ) {
                    Log.e( "HORARIOS", "URL erronea", e );
                } catch ( IOException e ) {
                    Log.e( "HORARIOS", "", e );
                }
            }
        }, 1000 );
    }

    private void descargarMapa(){
        mapView.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady( @NonNull MapboxMap mapboxMap ) {
                //        Offline Manager
                final OfflineManager manager = OfflineManager.getInstance( MainActivity.this );

                LatLngBounds puntos = new LatLngBounds.Builder()
                        .include( Places.DORRETXEA )
                        .include( Places.IKASTOLA )
                        .include( Places.LLODIO )
                        .include( Places.ELIZA )
                        .include( Places.PARKE )
                        .include( Places.ST_CRUZ )
                        .include( Places.ZERAMIKA )
                        .build();

                OfflineTilePyramidRegionDefinition def = new OfflineTilePyramidRegionDefinition(
                        getString( R.string.style_url ),
                        puntos,
                        10,
                        20,
                        MainActivity.this.getResources().getDisplayMetrics().density
                );



                manager.createOfflineRegion( def, null,
                        new OfflineManager.CreateOfflineRegionCallback() {
                    @Override
                    public void onCreate( OfflineRegion offlineRegion ) {
                        offlineRegion.setDownloadState( offlineRegion.STATE_ACTIVE );
                        offlineRegion.setObserver( new OfflineRegion.OfflineRegionObserver() {
                            @Override
                            public void onStatusChanged( OfflineRegionStatus status ) {
//                                  Calculate the download percentage
                                double percentage = status.getRequiredResourceCount() >= 0
                                        ? (100.0 * status.getCompletedResourceCount()
                                        / status.getRequiredResourceCount()) :
                                        0.0;
                                mapLoad.setProgress( (int) percentage );
                                if (status.isComplete()) {
//                                     Download complete
                                    mapLoad.setVisibility( View.INVISIBLE );
                                    Log.d("MAPBOX", "Region downloaded successfully.");
                                    Toast.makeText( MainActivity.this.getApplicationContext(),
                                            "Actualizados los mapas", Toast.LENGTH_SHORT ).show();
                                } else if (status.isRequiredResourceCountPrecise()) {
                                    Log.d("MAPBOX", percentage +"%");
                                }
                            }

                            @Override
                            public void onError( OfflineRegionError error ) {
                                Log.e("MapBox", "Descarga del mapa: " + error.getReason() );
                            }

                            @Override
                            public void mapboxTileCountLimitExceeded( long limit ) {

                            }
                        } );
                    }

                    @Override
                    public void onError( String error ) {
                        Log.e( "MAPBOX", error );
                    }
                } );
            }
        } );
    }

//    controlamos el evento del boton atras del
//    terminal
    @Override
    public void onBackPressed() {
        if( isPointView ){
            restoreCamera();
            setOnePointView( false );
        }else{
            finish();
        }
    }


//    Sobre escribimos los metodos de la actividad para que el mapa
//    interaccione con la actividad
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        manager.close();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        manager.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
