package com.pokalchemy.pokalchemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

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
	private boolean p_on = false, a_on = false, e_on = false, o_on = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		mPokemon = (Button)findViewById(R.id.pokemon_button);
		mPokemon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (p_on) {
					p_on = false;
					mPokemon.setTextColor(getResources().getColor(R.color.pokemon));
					mPokemon.setBackground(getResources().getDrawable(R.color.transparent));
					mPokemonRecyclerView.setVisibility(View.GONE);
				} else {
					p_on = true;
					mPokemon.setTextColor(getResources().getColor(R.color.colorAccent));
					mPokemon.setBackground(getResources().getDrawable(R.color.pokemon));
					mPokemonRecyclerView.setVisibility(View.VISIBLE);
				}
			}
		});

		mAnimals = (Button)findViewById(R.id.animals_button);
		mAnimals.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (a_on) {
					a_on = false;
					mAnimals.setTextColor(getResources().getColor(R.color.animals));
					mAnimals.setBackground(getResources().getDrawable(R.color.transparent));
					mAnimalRecyclerView.setVisibility(View.GONE);
				} else {
					a_on = true;
					mAnimals.setTextColor(getResources().getColor(R.color.colorAccent));
					mAnimals.setBackground(getResources().getDrawable(R.color.animals));
					mAnimalRecyclerView.setVisibility(View.VISIBLE);
				}
			}
		});

		mElements = (Button)findViewById(R.id.elements_button);
		mElements.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (e_on) {
					e_on = false;
					mElements.setTextColor(getResources().getColor(R.color.elements));
					mElements.setBackground(getResources().getDrawable(R.color.transparent));
					mElementRecyclerView.setVisibility(View.GONE);
				} else {
					e_on = true;
					mElements.setTextColor(getResources().getColor(R.color.colorAccent));
					mElements.setBackground(getResources().getDrawable(R.color.elements));
					mElementRecyclerView.setVisibility(View.VISIBLE);
				}
			}
		});

		mOther = (Button)findViewById(R.id.other_button);
		mOther.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (o_on) {
					o_on = false;
					mOther.setTextColor(getResources().getColor(R.color.other));
					mOther.setBackground(getResources().getDrawable(R.color.transparent));
					mOtherRecyclerView.setVisibility(View.GONE);
				} else {
					o_on = true;
					mOther.setTextColor(getResources().getColor(R.color.colorAccent));
					mOther.setBackground(getResources().getDrawable(R.color.other));
					mOtherRecyclerView.setVisibility(View.VISIBLE);
				}
			}
		});

		mPokemonRecyclerView = (RecyclerView) findViewById(R.id.pokemon_recycler_view);
		mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


		mAnimalRecyclerView = (RecyclerView) findViewById(R.id.animal_recycler_view);
		mAnimalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


		mElementRecyclerView = (RecyclerView) findViewById(R.id.element_recycler_view);
		mElementRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


		mOtherRecyclerView = (RecyclerView) findViewById(R.id.other_recycler_view);
		mOtherRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

		updateUI();
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
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		for(int i = 0; i < 25; i++)
		{
			ingredients.add(new Ingredient());
		}

		//setup mPokemon adapter
		if(mPokemonAdapter == null) {
			mPokemonAdapter = new IngredientAdapter(ingredients);
			mPokemonRecyclerView.setAdapter(mPokemonAdapter);
		}
		else {
			mPokemonAdapter.notifyDataSetChanged();
		}

		//setup animal adapter
		if(mAnimalAdapter == null) {
			mAnimalAdapter = new IngredientAdapter(ingredients);
			mAnimalRecyclerView.setAdapter(mAnimalAdapter);
		}
		else {
			mAnimalAdapter.notifyDataSetChanged();
		}

		//setup element adapter
		if(mElementAdapter == null) {
			mElementAdapter = new IngredientAdapter(ingredients);
			mElementRecyclerView.setAdapter(mElementAdapter);
		}
		else {
			mElementAdapter.notifyDataSetChanged();
		}

		//setup "mOther" adapter
		if(mOtherAdapter == null) {
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
					Log.d(LOG, "Clicked on Ingredient buttom");
					Toast.makeText(v.getContext(), "Added ingerdient to mixing area", Toast.LENGTH_LONG).show();
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


}
