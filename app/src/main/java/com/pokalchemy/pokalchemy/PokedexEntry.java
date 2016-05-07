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

    /**
     * Default constructor
     * <p>
     *     sets all fields to empty strings as a defualt
     * </p>
     */
    public PokedexEntry() {
        mIngredient = new Ingredient();
        mFirstIngredient = "";
        mSecondIngerdient = "";
        mThirdIngredient = "";
        mSensor = "";
        mDiscovered = false;
    }

    /**
     * Constructor with parameters
     * <p>
     *     sets all of the attributes of the entry according to the parameters passed in.
     * </p>
     * @param i ingredient
     * @param first ingredient that must be entered first
     * @param second ingredient that must be entered next
     * @param third ingredient that must be entered third
     * @param sensor if a sensor is needed to create this object/pokemon
     * @param discovered if the user has discovered or not
     */
    public PokedexEntry(Ingredient i, String first, String second, String third, String sensor, boolean discovered)
    {
        mIngredient = i;
        mFirstIngredient = first;
        mSecondIngerdient = second;
        mThirdIngredient = third;
        mSensor = sensor;
        mDiscovered = discovered;
    }

    /**
     * getter for Ingredient
     * @return ingredient
     */
    public Ingredient getIngredient() {
        return mIngredient;
    }

    /**
     * setter for Ingredient
     * @param mIngredient what the ingredient should be set to
     */
    public void setIngredient(Ingredient mIngredient) {
        this.mIngredient = mIngredient;
    }

    /**
     * getter for First Ingredient
     * @return first ingredient
     */
    public String getFirstIngredient() {
        return mFirstIngredient;
    }

    /**
     * Setter for first ingredient
     * @param mFirstIngredient
     */

    public void setFirstIngredient(String mFirstIngredient) {
        this.mFirstIngredient = mFirstIngredient;
    }

    /**
     * getter for Second Ingredient
     * @return second ingredient
     */
    public String getSecondIngerdient() {
        return mSecondIngerdient;
    }

    public void setSecondIngerdient(String mSecondIngerdient) {
        this.mSecondIngerdient = mSecondIngerdient;
    }

    /**
     * getter for Third Ingredient
     * @return third ingredient
     */
    public String getThirdIngredient() {
        return mThirdIngredient;
    }

    /**
     * Setter for third ingredient
     * @param mThirdIngredient what it should be set to
     */
    public void setThirdIngredient(String mThirdIngredient) {
        this.mThirdIngredient = mThirdIngredient;
    }


    /**
     * getter for Sensor field
     * @return sensor required for making
     */

    public String getSensor() {
        return mSensor;
    }

    /**
     * setter for sensor field
     * @param mSensor what the sensor required is if there is any
     */

    public void setSensor(String mSensor) {
        this.mSensor = mSensor;
    }

    /**
     * getter for is discovered
     * @return whether or not the pokemon has been discovered
     */

    public boolean isDiscovered() {
        return mDiscovered;
    }

    /**
     * setter for discovered
     * @param mDiscovered whether or not the user has discovered this pokemon
     */
    public void setDiscovered(boolean mDiscovered) {
        this.mDiscovered = mDiscovered;
    }
}
