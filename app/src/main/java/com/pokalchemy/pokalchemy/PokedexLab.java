package com.pokalchemy.pokalchemy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Connor on 5/3/2016.
 * This class handles the data for the app. Manages the database.
 */
public class PokedexLab {
    private static final String TAG = "LAB";
    private static PokedexLab sPokedexLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    /**
     * Create a new PokedexLab in the context
     * <p>Create a new PokedexLab in the context</p>
     * @param context the context we want out PokedexLab in
     * @return the new PokedexLab
     */
    public static PokedexLab get(Context context) {
        if (sPokedexLab == null) {
            sPokedexLab = new PokedexLab(context);
        }
        return sPokedexLab;
    }

    /**
     * PokedexLab constructor
     * <p>Sets the context and makes a new list of pokedexes</p>
     * @param context the context
     */
    private PokedexLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PokedexBaseHelper(mContext).getWritableDatabase();
    }


    /**
     * Add a pokedex to our list of pokedexes
     * <p>Add a pokedex to our list of pokedexes</p>
     * @param p the pokedex to add
     */
    public void addPokedex(PokedexEntry p){
        ContentValues values = getContentValues(p);

        mDatabase.insert(PokedexDBSchema.PokedexTable.NAME, null, values);

        Log.i(TAG, "Added to the database");
    }

    /**
     * get list of pokedexes
     * <p>get list of pokedexes</p>
     * @return list of pokedexes
     */
    public List<PokedexEntry> getPokedex() {
        List<PokedexEntry> pokedex = new ArrayList<>();

        PokedexCursorWrapper cursor = queryPokedex(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                Log.i(TAG, "Looking through DB");
                pokedex.add(cursor.getPokedexEntry());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return pokedex;
    }

    /**
     * get a pokedex form our list of pokedexes
     * <p>get a pokedex form our list of pokedexes</p>
     * @param entry the pokedex entry
     * @return the pokedex
     */
     public PokedexEntry getEntry(PokedexEntry entry){
        PokedexCursorWrapper cursor = queryPokedex(PokedexDBSchema.PokedexTable.Cols.FIRST_INGREDIENT + " = ? AND " +
                PokedexDBSchema.PokedexTable.Cols.SECOND_INGREDIENT + " = ? AND " +
                PokedexDBSchema.PokedexTable.Cols.THIRD_INGREDIENT + " = ? AND " +
                PokedexDBSchema.PokedexTable.Cols.SENSOR + " = ?",
                new String[]{entry.getFirstIngredient(), entry.getSecondIngerdient(), entry.getThirdIngredient(), entry.getSensor()});

        try {
            if( cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPokedexEntry();
        } finally {
            cursor.close();
        }

    }

    /**
     * Updates the date of a pokedex
     * <p>not used yet, but would update a marker at the same location</p>
     * @param pokedex the pokedexMarker to update based on
     */
    public void updatePokedex(PokedexEntry pokedex)
    {
        //TODO: update this function to change the discovered value
        String entry = pokedex.getIngredient().getName();
        ContentValues values = getContentValues(pokedex);

        mDatabase.update(PokedexDBSchema.PokedexTable.NAME, values, PokedexDBSchema.PokedexTable.Cols.NAME + " = ?", new String[]{entry});
    }

    /**
     * Constructs values to use with a database
     * <p>Constructs values to use with a database</p>
     * @param pokedex the pokedex we use with the database
     * @return the ContentValues
     */
    private static ContentValues getContentValues(PokedexEntry pokedex)
    {
        ContentValues values = new ContentValues();
        values.put(PokedexDBSchema.PokedexTable.Cols.NAME, pokedex.getIngredient().getName());
        values.put(PokedexDBSchema.PokedexTable.Cols.FIRST_INGREDIENT, pokedex.getFirstIngredient());
        values.put(PokedexDBSchema.PokedexTable.Cols.SECOND_INGREDIENT, pokedex.getSecondIngerdient());
        values.put(PokedexDBSchema.PokedexTable.Cols.THIRD_INGREDIENT, pokedex.getThirdIngredient());
        values.put(PokedexDBSchema.PokedexTable.Cols.SENSOR, pokedex.getSensor());

        if(pokedex.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.POKEMON)
        {
            values.put(PokedexDBSchema.PokedexTable.Cols.TYPE, "POKEMON");
        }
        else if(pokedex.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.ANIMAL)
        {
            values.put(PokedexDBSchema.PokedexTable.Cols.TYPE, "ANIMAL");
        }
        else if(pokedex.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.ELEMENT)
        {
            values.put(PokedexDBSchema.PokedexTable.Cols.TYPE, "ELEMENT");
        }
        else
        {
            values.put(PokedexDBSchema.PokedexTable.Cols.TYPE, "OTHER");
        }


        values.put(PokedexDBSchema.PokedexTable.Cols.IMAGE, pokedex.getIngredient().getOriginalImageID());
        values.put(PokedexDBSchema.PokedexTable.Cols.DISCOVERED, pokedex.isDiscovered());

        return values;
    }

    /**
     * Gets a CursorWrapper for our table
     * <p>Gets a CursorWrapper for our table</p>
     * @param whereClause the where clause for our query
     * @param whereArgs the where args for our query
     * @return a cursorWrapper for our table
     */
    private PokedexCursorWrapper queryPokedex(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                PokedexDBSchema.PokedexTable.NAME,
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
    public void clearPokedex()
    {
        mDatabase.delete(PokedexDBSchema.PokedexTable.NAME, null, null);
    }

}
