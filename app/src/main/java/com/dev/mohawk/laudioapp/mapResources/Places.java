package com.dev.mohawk.laudioapp.mapResources;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.dev.mohawk.laudioapp.R;
import com.mapbox.mapboxsdk.geometry.LatLng;

public final class Places {
    public static final LatLng IKASTOLA = new LatLng( 43.1464882, -2.961911 );
    public static final LatLng ELIZA = new LatLng( 43.1431389, -2.9623056 );
    public static final LatLng LLODIO = new LatLng( 43.14103,-2.9645557 );
    public static final LatLng TREN = new LatLng( 43.14263888, -2.96061111 );
    public static final LatLng ST_CRUZ = new LatLng(43.133027778, -2.9699444444);
    public static final LatLng ZERAMIKA = new LatLng(43.1335556, -2.969277778);
    public static final LatLng JAUREGIA = new LatLng(43.1335, -2.970805555556);
    public static final LatLng PARKE = new LatLng(43.1458611111, -2.9678611111);
    public static final LatLng DORRETXEA = new LatLng(43.14722222, -2.969166667);
    public static final int COUNT = 8;
    public static Context ctx;

    public static void setContext( Context context ){
        ctx = context;
    }

    public static String getName( LatLng place ){
        if( place == IKASTOLA )
            return ctx.getString(R.string.l_ikastola );
        if( place == ELIZA )
            return ctx.getString(R.string.l_petrieliza );
        if( place == TREN )
            return ctx.getString(R.string.l_tren );
        if( place == ST_CRUZ )
            return ctx.getString(R.string.l_cruz );
        if( place == ZERAMIKA )
            return ctx.getString(R.string.l_lantegia );
        if( place == JAUREGIA )
            return ctx.getString(R.string.l_jauregia );
        if( place == PARKE )
            return ctx.getString(R.string.l_parkea );
        if( place == DORRETXEA )
            return ctx.getString(R.string.l_dorretxea );
        else
            return ctx.getString(R.string.app_name );
    }

    public static int getId( LatLng place){
        if( place == Places.IKASTOLA )
            return 1;
        if( place == Places.ELIZA )
            return 2;
        if( place == Places.TREN )
            return 3;
        if( place == Places.ST_CRUZ )
            return 4;
        if( place == Places.ZERAMIKA )
            return 5;
        if( place == Places.JAUREGIA )
            return 6;
        if( place == Places.PARKE )
            return 7;
        if( place == Places.DORRETXEA )
            return 8;
        return 0;
    }

    public static LatLng getPlace( int id ){
        switch ( id ){
            case 1:
                return Places.IKASTOLA;
            case 2:
                return Places.ELIZA;
            case 3:
                return Places.TREN;
            case 4:
                return Places.ST_CRUZ;
            case 5:
                return Places.ZERAMIKA;
            case 6:
                return Places.JAUREGIA;
            case 7:
                return Places.PARKE;
            case 8:
                return Places.DORRETXEA;
            default:
                return null;
        }
    }

    public static boolean equals( LatLng loc1, LatLng loc2 ){
        double minlat = loc1.getLatitude() - 3;
        double maxlat = loc1.getLatitude() + 3;
        double minLong = loc1.getLongitude() - 3;
        double maxLong = loc1.getLongitude() + 3;
        double lat = loc2.getLatitude();
        double lgn = loc2.getLongitude();
        boolean latCheck = lat >= minlat && lat <= maxlat;
        boolean longCheck = lgn >= minLong && lgn <= maxLong;
        return latCheck && longCheck;
    }
}
