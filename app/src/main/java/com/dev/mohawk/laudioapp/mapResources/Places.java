package com.dev.mohawk.laudioapp.mapResources;

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

    public static String getName( LatLng place ){
        if( place == IKASTOLA )
            return "Ikastola";
        if( place == ELIZA )
            return "Eliza";
        if( place == TREN )
            return "Tren Geltoki";
        if( place == ST_CRUZ )
            return "Santa Cruz";
        if( place == ZERAMIKA )
            return "Zeramika Lantegia";
        if( place == JAUREGIA )
            return "Jauregia";
        if( place == PARKE )
            return "Parke";
        if( place == DORRETXEA )
            return "Dorretxea";
        else
            return "Llodio";
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
}
