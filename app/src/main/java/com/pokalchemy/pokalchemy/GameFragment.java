package com.pokalchemy.pokalchemy;

import android.content.Context;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

	private static final String LOG = "LOGGING";
	private static final String CHECK = "mixer area";
	private static final int SHAKE_THRESHOLD = 400;

	private RecyclerView mPokemonRecyclerView;
	private RecyclerView mAnimalRecyclerView;
	private RecyclerView mElementRecyclerView;
	private RecyclerView mOtherRecyclerView;

	private IngredientAdapter mPokemonAdapter;
	private IngredientAdapter mAnimalAdapter;
	private IngredientAdapter mElementAdapter;
	private IngredientAdapter mOtherAdapter;

	private SensorManager mSensorManager;
	private boolean isDark = false;
	private boolean isShaking = false;
	private long lastUpdate;
	private double x, y , z, last_x, last_y, last_z;
	private boolean isFlipped = false;

	private Button mPokemon, mAnimals, mElements, mOther;
	private ImageButton mTrash;
	private FrameLayout mMixer;
	private LinearLayout mMixingArea;
	private boolean p_on = false, a_on = false, e_on = false, o_on = false;

	private ArrayList<Ingredient> mMixerIngredients;

	private PokedexLab mPokedexLab;
	private ArrayList<PokedexEntry> mPokedex;
	private OrientationEventListener mOrientationEventListener;

	/**
	 * Create new instance
	 * <p>Make a new fragment for the class</p>
	 * @return the fragment
	 */
	public static GameFragment newInstance(){
		GameFragment fragment = new GameFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_game, container, false);
		mPokedexLab = PokedexLab.get(getContext());
		mPokedex = (ArrayList<PokedexEntry>) mPokedexLab.getPokedex();

		mMixerIngredients = new ArrayList<Ingredient>();

		mMixer = (FrameLayout)v.findViewById(R.id.ingredient_mixer_view);
		mMixingArea = (LinearLayout)v.findViewById(R.id.mixing_area);

		lastUpdate = 0;
		x = 0;
		y = 0;
		z = 0;
		last_x = 0;
		last_y = 0;
		last_z = 0;

		mPokemon = (Button)v.findViewById(R.id.pokemon_button);
		mPokemon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (p_on) {
					p_on = false;
					mPokemon.setTextColor(getResources().getColor(R.color.pokemon));
					mPokemon.setBackground(getResources().getDrawable(R.color.transparent));
					mPokemonRecyclerView.setVisibility(View.GONE);

					if(mPokemonAdapter != null && mPokemonAdapter.getItemCount() > 0) {
						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight + 1;
						mMixer.setLayoutParams(mixerParams);
					}
				} else {
					p_on = true;
					mPokemon.setTextColor(getResources().getColor(R.color.colorAccent));
					mPokemon.setBackground(getResources().getDrawable(R.color.pokemon));
					if(mPokemonAdapter != null && mPokemonAdapter.getItemCount() > 0) {
						mPokemonRecyclerView.setVisibility(View.VISIBLE);


						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight - 1;
						mMixer.setLayoutParams(mixerParams);
					}

				}
			}
		});

		mAnimals = (Button)v.findViewById(R.id.animals_button);
		mAnimals.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (a_on) {
					a_on = false;
					mAnimals.setTextColor(getResources().getColor(R.color.animal));
					mAnimals.setBackground(getResources().getDrawable(R.color.transparent));
					mAnimalRecyclerView.setVisibility(View.GONE);


					if(mAnimalAdapter != null && mAnimalAdapter.getItemCount() > 0) {
						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight + 1;
						mMixer.setLayoutParams(mixerParams);
					}
				} else {
					a_on = true;
					mAnimals.setTextColor(getResources().getColor(R.color.colorAccent));
					mAnimals.setBackground(getResources().getDrawable(R.color.animal));

					if(mAnimalAdapter != null && mAnimalAdapter.getItemCount() > 0) {
						mAnimalRecyclerView.setVisibility(View.VISIBLE);

						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight - 1;
						mMixer.setLayoutParams(mixerParams);
					}
				}
			}
		});

		mElements = (Button)v.findViewById(R.id.elements_button);
		mElements.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (e_on) {
					e_on = false;
					mElements.setTextColor(getResources().getColor(R.color.element));
					mElements.setBackground(getResources().getDrawable(R.color.transparent));
					mElementRecyclerView.setVisibility(View.GONE);

					if(mElementAdapter != null && mElementAdapter.getItemCount() > 0) {
						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight + 1;
						mMixer.setLayoutParams(mixerParams);
					}
				} else {
					e_on = true;
					mElements.setTextColor(getResources().getColor(R.color.colorAccent));
					mElements.setBackground(getResources().getDrawable(R.color.element));

					if(mElementAdapter != null && mElementAdapter.getItemCount() > 0) {
						mElementRecyclerView.setVisibility(View.VISIBLE);

						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight - 1;
						mMixer.setLayoutParams(mixerParams);
					}
				}
			}
		});

		mOther = (Button)v.findViewById(R.id.other_button);
		mOther.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (o_on) {
					o_on = false;
					mOther.setTextColor(getResources().getColor(R.color.other));
					mOther.setBackground(getResources().getDrawable(R.color.transparent));
					mOtherRecyclerView.setVisibility(View.GONE);


					if(mOtherAdapter != null && mOtherAdapter.getItemCount() > 0) {
						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight + 1;
						mMixer.setLayoutParams(mixerParams);
					}
				} else {
					o_on = true;
					mOther.setTextColor(getResources().getColor(R.color.colorAccent));
					mOther.setBackground(getResources().getDrawable(R.color.other));

					if(mOtherAdapter != null && mOtherAdapter.getItemCount() > 0) {
						mOtherRecyclerView.setVisibility(View.VISIBLE);

						LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams) mMixer.getLayoutParams();
						mixerParams.weight = mixerParams.weight - 1;
						mMixer.setLayoutParams(mixerParams);
					}
				}
			}
		});

		// Get sensor manager and register the listener to it.
		mSensorManager = ((SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE));

		Sensor light_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		mSensorManager.registerListener(lightSensorEventListener,
				light_sensor,
				SensorManager.SENSOR_DELAY_UI);
		Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(accelSensorEventListener,
				accelerometer,
				SensorManager.SENSOR_DELAY_UI);
		mOrientationEventListener = new OrientationEventListener(getContext(), mSensorManager.SENSOR_DELAY_UI) {
			@Override
			public void onOrientationChanged(int orientation) {
				if (170 < orientation && 190 > orientation){
					isFlipped = true;
					Log.i("Orientation", "upside down");
				}
				else {
					isFlipped = false;
					Log.i("Orientation", "right side up");
				}
			}
		};
		mOrientationEventListener.enable();


		mTrash = (ImageButton)v.findViewById(R.id.trash);
		mTrash.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMixerIngredients.clear();
				mMixingArea.removeAllViews();
			}
		});

		mPokemonRecyclerView = (RecyclerView)v.findViewById(R.id.pokemon_recycler_view);
		mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
		mPokemonRecyclerView.setBackgroundColor(getResources().getColor(R.color.pokemon));


		mAnimalRecyclerView = (RecyclerView)v.findViewById(R.id.animal_recycler_view);
		mAnimalRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
		mAnimalRecyclerView.setBackgroundColor(getResources().getColor(R.color.animal));


		mElementRecyclerView = (RecyclerView)v.findViewById(R.id.element_recycler_view);
		mElementRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
		mElementRecyclerView.setBackgroundColor(getResources().getColor(R.color.element));


		mOtherRecyclerView = (RecyclerView)v.findViewById(R.id.other_recycler_view);
		mOtherRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
		mOtherRecyclerView.setBackgroundColor(getResources().getColor(R.color.other));

		updateUI();

		return v;
	}


	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}


	/**
	 * Update the UI
	 * <p>update UI when crimes are edited to reflect changes in real time</p>
	 */
	public void updateUI() {

		//setup mPokemon adapter
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for(PokedexEntry entry : mPokedex)
		{
			if(entry.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.POKEMON && entry.isDiscovered()) {
				if(addIngredient(ingredients,entry))
				{
					ingredients.add(entry.getIngredient());
				}
			}
		}
		mPokemonAdapter = new IngredientAdapter(ingredients);
		mPokemonRecyclerView.setAdapter(mPokemonAdapter);

		//setup mAnimal adapter
		ingredients = new ArrayList<Ingredient>();
		for(PokedexEntry entry : mPokedex)
		{
			if(entry.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.ANIMAL && entry.isDiscovered()) {
				if(addIngredient(ingredients,entry))
				{
					ingredients.add(entry.getIngredient());
				}
			}
		}
		mAnimalAdapter = new IngredientAdapter(ingredients);
		mAnimalRecyclerView.setAdapter(mAnimalAdapter);

		//setup mElement adapter
		ingredients = new ArrayList<Ingredient>();
		for(PokedexEntry entry : mPokedex)
		{
			if(entry.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.ELEMENT && entry.isDiscovered()) {
				if(addIngredient(ingredients,entry))
				{
					ingredients.add(entry.getIngredient());
				}
			}
		}
		mElementAdapter = new IngredientAdapter(ingredients);
		mElementRecyclerView.setAdapter(mElementAdapter);


		//setup "mOther" adapter
		ingredients = new ArrayList<Ingredient>();
		for(PokedexEntry entry : mPokedex)
		{
			if(entry.getIngredient().getType() == Ingredient.INGREDIENT_TYPE.OTHER && entry.isDiscovered()) {
				if(addIngredient(ingredients,entry))
				{
					ingredients.add(entry.getIngredient());
				}
			}
		}
		mOtherAdapter = new IngredientAdapter(ingredients);
		mOtherRecyclerView.setAdapter(mOtherAdapter);

	}

	private boolean addIngredient(ArrayList<Ingredient> list, PokedexEntry entry)
	{
		for (Ingredient i : list) {
			if (i.getName().equals(entry.getIngredient().getName()))
				return false;
		}
		return true;
	}

	private class IngredientHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		private Ingredient mHolderIngredient;
		private ImageButton mButton;


		/**
		 * Constructor
		 * <p>Constructor given the view to use</p>
		 * @param itemView
		 */
		public IngredientHolder(View itemView){
			super(itemView);

			itemView.setOnClickListener(this);

			mButton = (ImageButton) itemView.findViewById(R.id.list_item_button);
			mButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.d(LOG, "Clicked on Ingredient button");
					if(mMixingArea.getChildCount() >= 3)
					{
						Toast.makeText(v.getContext(), "There are too many ingredients already. Cannot add more.", Toast.LENGTH_LONG).show();
						return;
					}

					mMixerIngredients.add(mHolderIngredient);

					ImageButton ingredientButton = new ImageButton(v.getContext());
					ingredientButton.setBackground(mButton.getDrawable());
					ingredientButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							mMixingArea.removeView(v);
							for (int i = 0; i < mMixerIngredients.size(); i++) {
								if (mMixerIngredients.get(i).getName() == mHolderIngredient.getName()) {
									mMixerIngredients.remove(i);
								}
							}
						}
					});
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					params.gravity = Gravity.CENTER;
					mMixingArea.addView(ingredientButton, params);

					if (checkMixer() != null) {
						//sdlfsh
					}
				}
			});

		}

		/**
		 * Binds a crime
		 * <p>Binds crime details to view</p>
		 * @param i the crime
		 */
		public void bindIngredient(Ingredient i){
			//TODO: Set the image here
			int id = getContext().getResources().getIdentifier(i.getImageID(), "drawable", getContext().getPackageName());
			mButton.setImageResource(id);
			mHolderIngredient = i;
			switch (mHolderIngredient.getType())
			{
				case POKEMON:
					mButton.setBackgroundColor(getResources().getColor(R.color.pokemon));
					break;
				case ANIMAL:
					mButton.setBackgroundColor(getResources().getColor(R.color.animal));
					break;
				case ELEMENT:
					mButton.setBackgroundColor(getResources().getColor(R.color.element));
					break;
				default:
					mButton.setBackgroundColor(getResources().getColor(R.color.other));
					break;
			}
		}

		/**
		 * On click listener
		 * <p>Let parent know a crime was seleced</p>
		 * @param v the view
		 */
		@Override
		public void onClick(View v) {
			//nothing?

		}
	}

	/**
	 * Ingredient adapter class
	 * <p>manages viewholders</p>
	 */
	private class IngredientAdapter extends RecyclerView.Adapter<IngredientHolder>{
		private List<Ingredient> mIngredients;

		/**
		 * Get number of crimes
		 * <p>Get number of crimes</p>
		 * @return
		 */
		@Override
		public int getItemCount() {
			return mIngredients.size();
		}

		/**
		 * Create viewholder
		 * <p>creates a viewholder and inflates it</p>
		 * @param parent parent for view
		 * @param viewType type for view
		 * @return a IngredientHolder
		 */
		@Override
		public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			//BUG: Next line
			LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
			View view = layoutInflater.inflate(R.layout.list_item, parent, false);
			return new IngredientHolder(view);
		}

		/**
		 * Binds Ingredient to holder
		 * <p>Binds a crime to a holder</p>
		 * @param holder the holder
		 * @param position the position of the holder
		 */
		@Override
		public void onBindViewHolder(IngredientHolder holder, int position) {
			Ingredient ingredient = mIngredients.get(position);
			holder.bindIngredient(ingredient);
		}

		/**
		 * Constructor
		 * <p>Gives the adapter the list of crimes</p>
		 * @param ingredients
		 */
		public IngredientAdapter(List<Ingredient> ingredients) {
			mIngredients = ingredients;
		}
	}

	/**
	 * Checks the mixing area for a valid combination
	 * @return the new combined ingredient or null if no combination
	 */
	private Ingredient checkMixer() {
		//create an entry to use to query the db
		PokedexEntry entry = new PokedexEntry();
		if(mMixerIngredients.size() >= 3)
		{
			entry.setThirdIngredient(mMixerIngredients.get(2).getName());
		}
		if(mMixerIngredients.size() >= 2)
		{
			entry.setSecondIngerdient(mMixerIngredients.get(1).getName());
		}
		if(mMixerIngredients.size() >= 1)
		{
			entry.setFirstIngredient(mMixerIngredients.get(0).getName());
		}

		//TODO: set sensor value here
		entry.setSensor("");


		//query the db
		PokedexEntry foundEntry = mPokedexLab.getEntry(entry);
		if(foundEntry != null)
		{
			//We found a match ~ go through pokedex and discover it!
			for(int i = 0; i < mPokedex.size(); i++)
			{
				if(mPokedex.get(i).getIngredient().getName().equals(foundEntry.getIngredient().getName()) && !mPokedex.get(i).isDiscovered())
				{
					Toast.makeText(getContext(), "You discovered " + foundEntry.getIngredient().getName(), Toast.LENGTH_SHORT).show();
					mPokedex.get(i).setDiscovered(true);
					mPokedexLab.updatePokedex(mPokedex.get(i));
				}
			}
			updateUI();
			return foundEntry.getIngredient();
		}

		//We didn't make anything without sensors... now try with
		if(isDark)
		{
			entry.setSensor("Dark");
		}
		//if(isFlipped)
		//{
		//	entry.set
		//}

		foundEntry = mPokedexLab.getEntry(entry);
		if(foundEntry != null)
		{
			//We found a match ~ go through pokedex and discover it!
			for(int i = 0; i < mPokedex.size(); i++)
			{
				if(mPokedex.get(i).getIngredient().getName().equals(foundEntry.getIngredient().getName()) && !mPokedex.get(i).isDiscovered())
				{
					Toast.makeText(getContext(), "You discovered " + foundEntry.getIngredient().getName(), Toast.LENGTH_SHORT).show();
					mPokedex.get(i).setDiscovered(true);
					mPokedexLab.updatePokedex(mPokedex.get(i));
				}
			}
			updateUI();
			return foundEntry.getIngredient();
		}

		return null;
	}

	// Define the sensor event listener.
	private SensorEventListener lightSensorEventListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float ambientLight = event.values[0];
			if(ambientLight < 30){
				isDark = true;
				checkMixer();
			}
			else
			{
				isDark = false;
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// ignore
		}
	};

	// Define the sensor event listener.
	private SensorEventListener accelSensorEventListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			PointF accel = new PointF(event.values[0], event.values[1]);
			long curr_time = System.currentTimeMillis();
			if((curr_time - lastUpdate) > 100) {
				long diffTime = (curr_time - lastUpdate);
				lastUpdate = curr_time;

				x = event.values[SensorManager.DATA_X];
				y = event.values[SensorManager.DATA_Y];
				z = event.values[SensorManager.DATA_Z];

				double speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 1000;

				if(speed > SHAKE_THRESHOLD) {
					//Toast.makeText(getActivity(), "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();;
					isShaking = true;
				}
				else {
					isShaking = false;
				}

				last_x = x;
				last_y = y;
				last_z = z;
			}
			//Log.d(TAG, "onSensorChanged() " + event.toString());
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// ignore
		}
	};

}
