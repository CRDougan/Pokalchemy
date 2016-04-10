package com.pokalchemy.pokalchemy;

/**
 * Created by Connor on 4/10/2016.
 */
public class Ingredient {
    private int mImageID;
    private String mName;
    private INGREDIENT_TYPE mType;

    public Ingredient()
    {
        mImageID = R.drawable.ic_human;
        mName = "Human";
        mType = INGREDIENT_TYPE.ANIMAL;
    }

    public int getImageID()
    {
        return mImageID;
    }




    public enum INGREDIENT_TYPE {POKEMON, ANIMAL, ELEMENT, OTHER};
}
