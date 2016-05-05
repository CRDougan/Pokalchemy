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

    public Ingredient(Ingredient other)
    {
        this.mImageID = other.getImageID();
        this.mName = other.getName();
        this.mType = other.getType();
    }

    public int getImageID()
    {
        return mImageID;
    }

    public void setImageID(int id)
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
