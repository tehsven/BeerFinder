package edu.umn.pinkpanthers.beerfinder.activities;

import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BeerActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.beer_screen);

		// Get parameter passed to Activity
		Beer beer = getIntent().getExtras().getParcelable(Beer.SELECTED_BEER);

		// Update displayed name
		TextView beerName = (TextView) findViewById(R.id.beer_toolbar_title);
		beerName.setText(beer.getName());

		// Update Hops image
		ImageView hops = (ImageView) findViewById(R.id.beer_hops_star);
		hops.setImageDrawable(getResources().getDrawable(
				getResources().getIdentifier("drawable/hops_" + beer.getHopsRank(), "drawable", getPackageName())));

		// Update body image
		ImageView body = (ImageView) findViewById(R.id.beer_body_star);
		body.setImageDrawable(getResources().getDrawable(
				getResources().getIdentifier("drawable/body_" + beer.getBodyRank(), "drawable", getPackageName())));

		// Update color image
		ImageView color = (ImageView) findViewById(R.id.beer_color_star);
		color.setImageDrawable(getResources().getDrawable(
				getResources().getIdentifier("drawable/color_" + beer.getColorRank(), "drawable", getPackageName())));

		// Update description
		TextView beerDescription = (TextView) findViewById(R.id.beer_desciption);
		beerDescription.setText(beer.getDescription());

	}

	// Tool bar back button clicked.
	public void backClicked(View view) {
		finish();
	}
}
