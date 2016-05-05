package com.pokalchemy.pokalchemy;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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

	private RecyclerView mPokemonRecyclerView;
	private RecyclerView mAnimalRecyclerView;
	private RecyclerView mElementRecyclerView;
	private RecyclerView mOtherRecyclerView;

	private IngredientAdapter mPokemonAdapter;
	private IngredientAdapter mAnimalAdapter;
	private IngredientAdapter mElementAdapter;
	private IngredientAdapter mOtherAdapter;

	private Button mPokemon, mAnimals, mElements, mOther;
	private ImageButton mTrash;
	private FrameLayout mMixer;
	private LinearLayout mMixingArea;
	private boolean p_on = false, a_on = false, e_on = false, o_on = false;


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

		//v.setContentView(R.layout.fragment_game);

		mMixer = (FrameLayout)v.findViewById(R.id.ingredient_mixer_view);
		mMixingArea = (LinearLayout)v.findViewById(R.id.mixing_area);

		mPokemon = (Button)v.findViewById(R.id.pokemon_button);
		mPokemon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (p_on) {
					p_on = false;
					mPokemon.setTextColor(getResources().getColor(R.color.pokemon));
					mPokemon.setBackground(getResources().getDrawable(R.color.transparent));
					mPokemonRecyclerView.setVisibility(View.GONE);

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight + 1;
					mMixer.setLayoutParams(mixerParams);
				} else {
					p_on = true;
					mPokemon.setTextColor(getResources().getColor(R.color.colorAccent));
					mPokemon.setBackground(getResources().getDrawable(R.color.pokemon));
					mPokemonRecyclerView.setVisibility(View.VISIBLE);

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight - 1;
					mMixer.setLayoutParams(mixerParams);
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

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight + 1;
					mMixer.setLayoutParams(mixerParams);
				} else {
					a_on = true;
					mAnimals.setTextColor(getResources().getColor(R.color.colorAccent));
					mAnimals.setBackground(getResources().getDrawable(R.color.animal));
					mAnimalRecyclerView.setVisibility(View.VISIBLE);

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight - 1;
					mMixer.setLayoutParams(mixerParams);
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

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight + 1;
					mMixer.setLayoutParams(mixerParams);
				} else {
					e_on = true;
					mElements.setTextColor(getResources().getColor(R.color.colorAccent));
					mElements.setBackground(getResources().getDrawable(R.color.element));
					mElementRecyclerView.setVisibility(View.VISIBLE);

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight - 1;
					mMixer.setLayoutParams(mixerParams);
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

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight + 1;
					mMixer.setLayoutParams(mixerParams);
				} else {
					o_on = true;
					mOther.setTextColor(getResources().getColor(R.color.colorAccent));
					mOther.setBackground(getResources().getDrawable(R.color.other));
					mOtherRecyclerView.setVisibility(View.VISIBLE);

					LinearLayout.LayoutParams mixerParams = (LinearLayout.LayoutParams)mMixer.getLayoutParams();
					mixerParams.weight = mixerParams.weight - 1;
					mMixer.setLayoutParams(mixerParams);
				}
			}
		});

		mTrash = (ImageButton)v.findViewById(R.id.trash);
		mTrash.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMixingArea.removeAllViews();
			}
		});

		mPokemonRecyclerView = (RecyclerView)v.findViewById(R.id.pokemon_recycler_view);
		mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


		mAnimalRecyclerView = (RecyclerView)v.findViewById(R.id.animal_recycler_view);
		mAnimalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


		mElementRecyclerView = (RecyclerView)v.findViewById(R.id.element_recycler_view);
		mElementRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


		mOtherRecyclerView = (RecyclerView)v.findViewById(R.id.other_recycler_view);
		mOtherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

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
		if(mPokemonAdapter == null) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			for(int i = 0; i < 11; i++)
			{
				Ingredient ingredient = new Ingredient();
				ingredient.setImageID(R.mipmap.ic_launcher);
				ingredients.add(ingredient);
			}
			mPokemonAdapter = new IngredientAdapter(ingredients);
			mPokemonRecyclerView.setAdapter(mPokemonAdapter);
		}
		else {
			mPokemonAdapter.notifyDataSetChanged();
		}

		//setup mAnimal adapter
		if(mAnimalAdapter == null) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			for(int i = 0; i < 20; i++)
			{
				Ingredient ingredient = new Ingredient();
				ingredient.setImageID(R.drawable.ic_human);
				ingredients.add(ingredient);
			}
			mAnimalAdapter = new IngredientAdapter(ingredients);
			mAnimalRecyclerView.setAdapter(mAnimalAdapter);
		}
		else {
			mAnimalAdapter.notifyDataSetChanged();
		}

		//setup mElement adapter
		if(mElementAdapter == null) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			for(int i = 0; i < 100; i++)
			{
				Ingredient ingredient = new Ingredient();
				ingredient.setImageID(R.drawable.ic_water);
				ingredients.add(ingredient);
			}
			mElementAdapter = new IngredientAdapter(ingredients);
			mElementRecyclerView.setAdapter(mElementAdapter);
		}
		else {
			mElementAdapter.notifyDataSetChanged();
		}

		//setup "mOther" adapter
		if(mOtherAdapter == null) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
//			for(int i = 0; i < 3; i++)
//			{
//				Ingredient ingredient = new Ingredient();
//				ingredient.setImageID(R.mipmap.ic_launcher);
//				ingredients.add(ingredient);
//			}
			mOtherAdapter = new IngredientAdapter(ingredients);
			mOtherRecyclerView.setAdapter(mOtherAdapter);
		}
		else {
			mOtherAdapter.notifyDataSetChanged();
		}

	}

	private class IngredientHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


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
					Toast.makeText(v.getContext(), String.valueOf(mButton.getBackground()), Toast.LENGTH_LONG).show();

					ImageButton ingredientButton = new ImageButton(v.getContext());
					ingredientButton.setBackground(mButton.getDrawable());
					ingredientButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							mMixingArea.removeView(v);
						}
					});
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					params.gravity = Gravity.CENTER;
					mMixingArea.addView(ingredientButton, params);

					if (checkMixer() != null) {
						Toast.makeText(v.getContext(), "You made a new Pokemon!", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

		/**
		 * Binds a crime
		 * <p>Binds crime details to view</p>
		 * @param i the crime
		 */
		public void bindCrime(Ingredient i){
			//TODO: Set the image here
			mButton.setImageResource(i.getImageID());
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
	 * Crime adapter class
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
		 * @return a CrimeHolder
		 */
		@Override
		public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			//BUG: Next line
			LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
			View view = layoutInflater.inflate(R.layout.list_item, parent, false);
			return new IngredientHolder(view);
		}

		/**
		 * Binds Crime to holder
		 * <p>Binds a crime to a holder</p>
		 * @param holder the holder
		 * @param position the position of the holder
		 */
		@Override
		public void onBindViewHolder(IngredientHolder holder, int position) {
			Ingredient ingredient = mIngredients.get(position);
			holder.bindCrime(ingredient);
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
		int waterCount = 0, humanCount = 0;
		for (int i = 0; i < mMixingArea.getChildCount(); i++) {
//			mMixingArea.getChildAt(i).getResources();
		}
		if (waterCount == 1 && humanCount == 1) {
			return new Ingredient();
		}
		return null;
	}
}
