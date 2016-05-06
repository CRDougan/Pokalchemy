package com.pokalchemy.pokalchemy;

/**
 * Created by Connor on 4/10/2016.
 */
public class Ingredient {
    private String mImageID;
    private String mName;
    private INGREDIENT_TYPE mType;


    public Ingredient()
    {
        mImageID = "";
        mName = "";
        mType = INGREDIENT_TYPE.OTHER;
    }

    public Ingredient(Ingredient other)
    {
        this.mImageID = other.getImageID();
        this.mName = other.getName();
        this.mType = other.getType();
    }

    public String getImageID()
    {
        return mImageID;
    }

    public void setImageID(String id)
    {
        mImageID = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public INGREDIENT_TYPE getType() {
        return mType;
    }

    public void setType(INGREDIENT_TYPE mType) {
        this.mType = mType;
    }



    public enum INGREDIENT_TYPE {POKEMON, ANIMAL, ELEMENT, OTHER};
}
