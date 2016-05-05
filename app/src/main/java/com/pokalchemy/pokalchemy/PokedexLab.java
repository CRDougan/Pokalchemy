package com.pokalchemy.pokalchemy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 5/3/2016.
 * This class handles the data for the app. Manages the database.
 */
public class PokedexLab {
    private static final String TAG = "LAB";
    private static PokedexLab sPlacesLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * Create a new PokedexLab in the context
     * <p>Create a new PokedexLab in the context</p>
     * @param context the context we want out PokedexLab in
     * @return the new PokedexLab
     */
    public static PokedexLab get(Context context) {
        if (sPlacesLab == null) {
            sPlacesLab = new PokedexLab(context);
        }
        return sPlacesLab;
    }

    /**
     * PokedexLab constructor
     * <p>Sets the context and makes a new list of places</p>
     * @param context the context
     */
    private PokedexLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PokedexBaseHelper(mContext).getWritableDatabase();
    }


    /**
     * Add a place to our list of places
     * <p>Add a place to our list of places</p>
     * @param p the place to add
     */
    public void addPlaces(PlaceMarker p){
        ContentValues values = getContentValues(p);

        mDatabase.insert(PokedexDBSchema.PlacesTable.NAME, null, values);

        Log.i(TAG, "Added to the database");
    }

    /**
     * Remove a place from our list of places
     * <p>Remove a place from our list of places</p>
     * @param p the place to remove
     */
    public void removePlaces(PlaceMarker p){
//        for(int i = 0; i < mPlacess.size(); i++){
//            if(c.getUUID().equals(mPlacess.get(i).getUUID())){
//                mPlacess.remove(i);
//            }
//        }
    }

    /**
     * get list of places
     * <p>get list of places</p>
     * @return list of places
     */
    public List<PlaceMarker> getPlaces() {
        List<PlaceMarker> places = new ArrayList<>();

        PokedexCursorWrapper cursor = queryPlaces(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                Log.i(TAG, "Looking through DB");
                places.add(cursor.getPlace());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return places;
    }

    /**
     * get a place form our list of places
     * <p>get a place form our list of places</p>
     * @param latLng the location of the place
     * @return the place
     */
    public PlaceMarker getPlace(LatLng latLng){
        PokedexCursorWrapper cursor = queryPlaces(PokedexDBSchema.PlacesTable.Cols.LAT + " = ? AND " + PokedexDBSchema.PlacesTable.Cols.LON + " = ?",
                    new String[]{Double.toString(latLng.latitude), Double.toString((double) latLng.longitude)});

        try {
            if( cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPlace();
        } finally {
            cursor.close();
        }

    }

    /**
     * Updates the date of a place
     * <p>not used yet, but would update a marker at the same location</p>
     * @param place the placeMarker to update based on
     */
    public void updatePlaces(PlaceMarker place)
    {
        String uuidString = place.getDatetime().toString();
        ContentValues values = getContentValues(place);

        mDatabase.update(PokedexDBSchema.PlacesTable.NAME, values, PokedexDBSchema.PlacesTable.Cols.DATETIME + " = ?", new String[]{uuidString});
    }

    /**
     * Constructs values to use with a database
     * <p>Constructs values to use with a database</p>
     * @param place the place we use with the database
     * @return the ContentValues
     */
    private static ContentValues getContentValues(PlaceMarker place)
    {
        ContentValues values = new ContentValues();
        values.put(PokedexDBSchema.PlacesTable.Cols.LAT, (double) place.getLatLng().latitude);
        values.put(PokedexDBSchema.PlacesTable.Cols.LON, (double) place.getLatLng().longitude);
        values.put(PokedexDBSchema.PlacesTable.Cols.DATETIME, place.getDatetime());
        values.put(PokedexDBSchema.PlacesTable.Cols.TEMP, (double) place.getTemp());
        values.put(PokedexDBSchema.PlacesTable.Cols.WEATHER, place.getWeather());

        return values;
    }

    /**
     * Gets a CursorWrapper for our table
     * <p>Gets a CursorWrapper for our table</p>
     * @param whereClause the where clause for our query
     * @param whereArgs the where args for our query
     * @return a cursorWrapper for our table
     */
    private PokedexCursorWrapper queryPlaces(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                PokedexDBSchema.PlacesTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new PokedexCursorWrapper(cursor);
    }

    /**
     * Clears our database table
     * <p>Clears the db table.</p>
     */
    public void clearPlaces()
    {
        mDatabase.delete(PokedexDBSchema.PlacesTable.NAME, null, null);
    }

}
