package com.pokalchemy.pokalchemy;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;

/**
 * This activity hosts a GameFragment
 * <p>This activity hosts a GameFragment</p>
 */
public class GameActivity extends SingleFragmentActivity {

    private static final int REQUEST_ERROR = 0;

    /**
     * onCreate
     * <p>onCreate for the activity</p>
     * @param savedInstanceState the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    /**
     * Makes a fragment to host
     * <p>Makes a fragment to host</p>
     * @return a fragment to host
     */
    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }


}
