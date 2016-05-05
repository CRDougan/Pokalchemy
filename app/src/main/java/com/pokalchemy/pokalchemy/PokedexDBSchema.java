package com.pokalchemy.pokalchemy;

/**
 * Created by Connor on 5/3/2016.
 * The Schema for our Places Table
 * <p>The Schema for our Places Table</p>
 */
public class PokedexDBSchema {
    /**
     * The actual Places table
     * <p>the Place table</p>
     */
    public static final class PokedexTable {
        public static final String NAME = "pokedex";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String FIRST_INGREDIENT = "first";
            public static final String SECOND_INGREDIENT = "second";
            public static final String THIRD_INGREDIENT = "third";
            public static final String SENSOR = "sensor";
            public static final String TYPE = "type";
            public static final String IMAGE = "image";
            public static final String DISCOVERED = "discovered";
        }
    }
}
