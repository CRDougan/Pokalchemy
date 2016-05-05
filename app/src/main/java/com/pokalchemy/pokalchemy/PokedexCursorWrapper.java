package com.pokalchemy.pokalchemy;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Connor on 5/3/2016.
 * A cursorWrapper wrapper
 * <p>The lets us get a row of data from our table</p>
 */
public class PokedexCursorWrapper extends CursorWrapper{
        public PokedexCursorWrapper(Cursor cursor)
        {
            super(cursor);
        }


    /**
     * Gets a row of data from our table
     * <p>Gets a row of data from our table and returns it as an object</p>
     * @return An onject representation of the data
     */
        public Ingredient getPokedex(){
            String name = getString(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.NAME));
            String first = getString(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.FIRST_INGREDIENT));
            String second = getString(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.SECOND_INGREDIENT));
            String third = getString(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.THIRD_INGREDIENT));
            String sensor = getString(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.SENSOR));
            int type = getInt(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.TYPE));
            int image = getInt(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.IMAGE));
            int discovered = getInt(getColumnIndex(PokedexDBSchema.PokedexTable.Cols.DISCOVERED));

            //TODO: make this work with ingredient
            //Do this by making a "PokedexEntry" object with Ingredient and sensor, discovered, first,...
            Ingredient ingredient = new Ingredient();
            ingredient.setName(name);
            ingredient.setImageID(image);
            switch (type)
            {
                case 0:
                    ingredient.setType(Ingredient.INGREDIENT_TYPE.POKEMON);
                    break;
                case 1:
                    ingredient.setType(Ingredient.INGREDIENT_TYPE.ANIMAL);
                    break;
                case 2:
                    ingredient.setType(Ingredient.INGREDIENT_TYPE.ELEMENT);
                    break;
                default:
                    ingredient.setType(Ingredient.INGREDIENT_TYPE.OTHER);
                    break;
            }


            return ingredient;
        }
    }
