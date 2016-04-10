package com.pokalchemy.pokalchemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

	Button pokemon, animals, elements, other;
	boolean p_on = false, a_on = false, e_on = false, o_on = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		pokemon = (Button)findViewById(R.id.pokemon_button);
		pokemon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (p_on) {
					p_on = false;
					pokemon.setTextColor(getResources().getColor(R.color.pokemon));
					pokemon.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					p_on = true;
					pokemon.setTextColor(getResources().getColor(R.color.colorAccent));
					pokemon.setBackground(getResources().getDrawable(R.color.pokemon));
				}
			}
		});

		animals = (Button)findViewById(R.id.animals_button);
		animals.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (a_on) {
					a_on = false;
					animals.setTextColor(getResources().getColor(R.color.animals));
					animals.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					a_on = true;
					animals.setTextColor(getResources().getColor(R.color.colorAccent));
					animals.setBackground(getResources().getDrawable(R.color.animals));
				}
			}
		});

		elements = (Button)findViewById(R.id.elements_button);
		elements.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (e_on) {
					e_on = false;
					elements.setTextColor(getResources().getColor(R.color.elements));
					elements.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					e_on = true;
					elements.setTextColor(getResources().getColor(R.color.colorAccent));
					elements.setBackground(getResources().getDrawable(R.color.elements));
				}
			}
		});

		other = (Button)findViewById(R.id.other_button);
		other.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (o_on) {
					o_on = false;
					other.setTextColor(getResources().getColor(R.color.other));
					other.setBackground(getResources().getDrawable(R.color.transparent));
				} else {
					o_on = true;
					other.setTextColor(getResources().getColor(R.color.colorAccent));
					other.setBackground(getResources().getDrawable(R.color.other));
				}
			}
		});
	}


}
