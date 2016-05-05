package com.pokalchemy.pokalchemy;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Connor on 5/3/2016.
 * Helps make the database
 */
public class PokedexBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String TAG = "pokedexBaseHelper";
    private static final String DATABASE_NAME = "pokedexBase.db";
    private Context mContext;

    /**
     * Loads the database if it exists
     * <p>loads the database</p>
     * @param context the context
     */
    public PokedexBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
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

        //Insert the default rows of the database upon initial creation
        //Add default records to animals
        ContentValues values = new ContentValues();
        //Get xml resource file
        Resources res = mContext.getResources();

        //Open xml file
        XmlResourceParser xml = res.getXml(R.xml.pokedex);
        try
        {
            //Check for end of document
            int eventType = xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) &&(xml.getName().equals("record"))){
                    //Record tag found, now get values and insert record
                    String title = xml.getAttributeValue(null, PokedexDBSchema.PokedexTable.NAME);
                    String color = xml.getAttributeValue(null, PokedexDBSchema.PokedexTable.NAME);
                    values.put(PokedexDBSchema.PokedexTable.NAME, title);
                    values.put(PokedexDBSchema.PokedexTable.NAME, color);
                    db.insert(PokedexDBSchema.PokedexTable.NAME, null, values);
                }
                eventType = xml.next();
            }
        }
        //Catch errors
        catch (XmlPullParserException e)
        {
            Log.e(TAG, e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);

        }
        finally
        {
            //Close the xml file
            xml.close();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}