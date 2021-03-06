package com.pokalchemy.pokalchemy;

/**
 * Created by Connor on 4/10/2016.
 * <p>Ingredient class used in creating new Pokemon (also ingredients) with imageID, name, and type</p>
 */
public class Ingredient {
    private String mImageID;
    private String mOriginalImageID;
    private String mName;
    private INGREDIENT_TYPE mType;

    /**
     * Constructs a default Ingredient
     */
    public Ingredient()
    {
        mImageID = "";
        mName = "";
        mType = INGREDIENT_TYPE.OTHER;
    }

    /**
     * Constructs a copy of another Ingredient
     * @param other copied ingredient
     */
    public Ingredient(Ingredient other)
    {
        this.mImageID = other.getImageID();
        this.mName = other.getName();
        this.mType = other.getType();
        this.mOriginalImageID = other.getOriginalImageID();
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

    public String getOriginalImageID() {
        return mOriginalImageID;
    }

    public void setOriginalImageID(String mOriginalImageID) {
        this.mOriginalImageID = mOriginalImageID;
    }

    public enum INGREDIENT_TYPE {POKEMON, ANIMAL, ELEMENT, OTHER};
}
