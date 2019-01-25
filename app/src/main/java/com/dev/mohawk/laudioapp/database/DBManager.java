package com.dev.mohawk.laudioapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.mohawk.laudioapp.mapResources.Places;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class DBManager extends SQLiteOpenHelper {
//    nombre de las tablas correspondientes
    public static final String LAST_POINT_TABLE = "last_point";
    public static final String ACTIVITIES_DATA_TABLE = "act_data";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
//        Se ejecutará únicamente la primera vez que se crea la base de datos
//        SQL a ejecutar:
        String createLastPointSQL = " CREATE TABLE " + LAST_POINT_TABLE +
                "( id  INTEGER, nombre TEXT )";
        String createActSQL = "CREATE TABLE " + ACTIVITIES_DATA_TABLE +
                "( id INTEGER PRIMARY KEY, intents INTEGER, done NUMERIC, time TEXT )";
        String insertFirstPointSQL = "INSERT INTO " + LAST_POINT_TABLE + " VALUES( 10, '"
                + Places.getName( Places.getPlace( 1 ) ) + "')";
//        creamos la tabla par guardar el último punto
        db.execSQL( createLastPointSQL );
//        creamos la tabla hist'orica de las actividades, guradamos los resultados
        db.execSQL( createActSQL );
//        insertamos el primer dato en la tabla last_point
        db.execSQL( insertFirstPointSQL );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
//        se ejecuta cuando se actualiza la version de la base de datos
//        Nosotros borramos la tabla
        db.execSQL( "DROP TABLE " + ACTIVITIES_DATA_TABLE );
        db.execSQL( "DROP TABLE " + LAST_POINT_TABLE );
//        y las volvemos a crear
        onCreate( db );
    }

    /**
     * Hace una petición a la base de datos SQLite para recuperar el id de la última posición
     * @return el último marker guardado
     */
    public LatLng getLastPoint(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + LAST_POINT_TABLE, null );
        int id = -1;
        if( cursor.moveToFirst() ){
            id = cursor.getInt( 0 );
//            los id se almacenan con un formato en específico por lo que deberemos tenerlo en cuenta
//            intentamos coger el primer número
           try {
               String num = String.valueOf( id ).substring( 0,1 );
//               el primer número hace referencia al marcador para el mapa
               id = Integer.parseInt( num );

           }finally {
               return Places.getPlace( id );
           }
        } else{
            return null;
        }
    }

    /**
     *
     * @param id de la última actividad realizada
     * @return si se ha actualizado el dato
     */
    public boolean updateLastPoint( int id ){
        String name;
        try {
            String tempNum = String.valueOf( id ).substring( 0,1 );
            name = Places.getName( Places.getPlace( Integer.parseInt( tempNum ) ) );
        }catch (StringIndexOutOfBoundsException e){
            name = Places.getName( Places.getPlace( id ) );
        }
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL( "DELETE FROM " + LAST_POINT_TABLE );
        ContentValues val = new ContentValues();
        val.put( "id", id );
        val.put( "nombre", name );
        long result = db.insert( LAST_POINT_TABLE, null, val );
        return result != 0;
    }

    /**
     * Reinicia todos los datos de la base de datos
     */
    public void restartData(){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.execSQL( "DELETE FROM " + ACTIVITIES_DATA_TABLE );
        db.endTransaction();
        this.updateLastPoint( 10 );
    }

    public int getLastActivity(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c =db.rawQuery( "SELECT * FROM " + LAST_POINT_TABLE, null );
        if(c.moveToFirst() )
            return c.getInt( 0 );
        else
            return -1;
    }

}
