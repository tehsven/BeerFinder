package edu.umn.pinkpanthers.beerfinder.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import edu.umn.pinkpanthers.beerfinder.data.Venue;
import edu.umn.pinkpanthers.beerfinder.network.BeerFinderWebService;
import edu.umn.pinkpanthers.beerfinder.network.Callback;

public class VenueActivity extends Activity implements Callback <Beer>{

	private TextView nameView;
	private TextView addressView;
	private TextView phoneNumber;
	private Venue venue;
	
	private LayoutInflater inflater;
	private ViewGroup beerViewGroup;	// list of hold all the beer UI elements

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		venue = getIntent().getExtras().getParcelable(Venue.SELECTED_VENUE);

		this.setContentView(R.layout.venue_screen);

		nameView = (TextView) findViewById(R.id.venue_toolbar_title);
		addressView = (TextView) findViewById(R.id.venue_address_label);
		phoneNumber = (TextView) findViewById(R.id.venue_phone_number_label);

		updateView();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void updateView() {
		nameView.setText(venue.getName());
		addressView.setText(venue.getAddress());
		phoneNumber.setText(venue.getPhoneNumber());

		createBeerList();
	}

	private void createBeerList() {
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		beerViewGroup = (ViewGroup) findViewById(R.id.venue_beer_list_group);

		// Get data for each of the beers from the BeerFinderWebService
		for (String beerID : venue.getBeers()) {
			BeerFinderWebService.getInstance().getBeerInfoFromServer(beerID, this);
		}
			
	}

	public void homeClicked(View view) {
		finish();
	}

	@Override
	public void onSuccess(final Beer beer) {
		if(beer != null){
			runOnUiThread(new Runnable() {
			     public void run() {
			    	// Update display with the most recently returned beer
			 		View beerView = inflater.inflate(R.layout.venue_beer_list_item, null);
			 		beerView.setTag(beer);
	
			 		((TextView) beerView.findViewById(R.id.venue_beer_list_item_name)).setText(beer.getName());
			 		((TextView) beerView.findViewById(R.id.venue_beer_list_item_brewery)).setText(beer.getBreweryName());
	
			 		beerView.setOnClickListener(new OnClickListener() {
			 			@Override
			 			public void onClick(View view) {
			 				Intent beerIntent = new Intent(getApplicationContext(), BeerActivity.class);
			 				beerIntent.putExtra(Beer.SELECTED_BEER, (Beer) view.getTag());
			 				startActivity(beerIntent);
			 			}
			 		});
	
			 		beerViewGroup.addView(beerView);
			    }
			});
		}	
	}

	@Override
	public void onFailure(Beer value) {
		// Toast message on failure
	}

}
