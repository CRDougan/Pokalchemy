package com.pokalchemy.pokalchemy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Connor on 5/3/2016.
 * Helps make the database
 */
public class PokedexBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pokedexBase.db";

    /**
     * Loads the database if it exists
     * <p>loads the database</p>
     * @param context the context
     */
    public PokedexBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * onCreate
     * <p>creates the database</p>
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PokedexDBSchema.PokedexTable.NAME + "(" + " _id integer primary key autoincrement, " +
                PokedexDBSchema.PokedexTable.Cols.NAME + ", " +
                PokedexDBSchema.PokedexTable.Cols.FIRST_INGREDIENT + ", " +
                PokedexDBSchema.PokedexTable.Cols.SECOND_INGREDIENT + ", " +
                PokedexDBSchema.PokedexTable.Cols.THIRD_INGREDIENT + ", " +
                PokedexDBSchema.PokedexTable.Cols.SENSOR + ", " +
                PokedexDBSchema.PokedexTable.Cols.TYPE + ", " +
                PokedexDBSchema.PokedexTable.Cols.IMAGE + ", " +
                PokedexDBSchema.PokedexTable.Cols.DISCOVERED + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}