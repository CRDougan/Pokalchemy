package com.pokalchemy.pokalchemy;

/**
 * Created by Connor on 5/5/2016.
 */
public class PokedexEntry {
    private Ingredient mIngredient;
    private String mFirstIngredient;
    private String mSecondIngerdient;
    private String mThirdIngredient;
    private String mSensor;
    private boolean mDiscovered;

    public PokedexEntry() {
        mIngredient = null;
        mFirstIngredient = null;
        mSecondIngerdient = null;
        mThirdIngredient = null;
        mSensor = null;
        mDiscovered = false;
    }

    public PokedexEntry(Ingredient i, String first, String second, String third, String sensor, boolean discovered)
    {
        mIngredient = i;
        mFirstIngredient = first;
        mSecondIngerdient = second;
        mThirdIngredient = third;
        mSensor = sensor;
        mDiscovered = discovered;
    }

    public Ingredient getIngredient() {
        return mIngredient;
    }

    public void setIngredient(Ingredient mIngredient) {
        this.mIngredient = mIngredient;
    }

    public String getFirstIngredient() {
        return mFirstIngredient;
    }

    public void setFirstIngredient(String mFirstIngredient) {
        this.mFirstIngredient = mFirstIngredient;
    }

    public String getSecondIngerdient() {
        return mSecondIngerdient;
    }

    public void setSecondIngerdient(String mSecondIngerdient) {
        this.mSecondIngerdient = mSecondIngerdient;
    }

    public String getThirdIngredient() {
        return mThirdIngredient;
    }

    public void setThirdIngredient(String mThirdIngredient) {
        this.mThirdIngredient = mThirdIngredient;
    }

    public String getSensor() {
        return mSensor;
    }

    public void setSensor(String mSensor) {
        this.mSensor = mSensor;
    }

    public boolean isDiscovered() {
        return mDiscovered;
    }

    public void setDiscovered(boolean mDiscovered) {
        this.mDiscovered = mDiscovered;
    }
}
