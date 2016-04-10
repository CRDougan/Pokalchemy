package com.pokalchemy.pokalchemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity {

	private Button mPokemon, mAnimal, mElement, mOther;
	private ImageButton mTrash;
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
				} else {
					p_on = true;
					mPokemon.setTextColor(getResources().getColor(R.color.colorAccent));
					mPokemon.setBackground(getResources().getDrawable(R.color.pokemon));
				}
			}
		});

		mAnimal = (Button)findViewById(R.id.animal_button);
		mAnimal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (a_on) {
					a_on = false;
					mAnimal.setTextColor(getResources().getColor(R.color.animal));
					mAnimal.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					a_on = true;
					mAnimal.setTextColor(getResources().getColor(R.color.colorAccent));
					mAnimal.setBackground(getResources().getDrawable(R.color.animal));
				}
			}
		});

		mElement = (Button)findViewById(R.id.element_button);
		mElement.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (e_on) {
					e_on = false;
					mElement.setTextColor(getResources().getColor(R.color.element));
					mElement.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					e_on = true;
					mElement.setTextColor(getResources().getColor(R.color.colorAccent));
					mElement.setBackground(getResources().getDrawable(R.color.element));
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
				} else {
					o_on = true;
					mOther.setTextColor(getResources().getColor(R.color.colorAccent));
					mOther.setBackground(getResources().getDrawable(R.color.other));
				}
			}
		});

		mTrash = (ImageButton)findViewById(R.id.trash);
		mTrash.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// #TODO delete all ingredients in mixing area
			}
		});
	}


}
