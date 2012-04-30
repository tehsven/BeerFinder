package edu.umn.pinkpanthers.beerfinder.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.UserLocation;
import edu.umn.pinkpanthers.beerfinder.network.BeerFinderWebService;

/**
 * The home screen of the application.
 * 
 */
public class SearchActivity extends Activity implements OnClickListener {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_screen);
		findViewById(R.id.search_by_brand_button).setOnClickListener(this);
		findViewById(R.id.search_by_zip_button).setOnClickListener(this);
		findViewById(R.id.change_location).setOnClickListener(this);
		BeerFinderWebService.initialize();
		UserLocation.initialize();
	}

	public void onClick(View v) {
		// Perform a search
		if (v.getId() == R.id.search_by_brand_button) {
			if(UserLocation.getInstance().isLocationValid() == false){
				Toast.makeText(this, "Invalid location, please select a custom location to continue.", Toast.LENGTH_SHORT).show();
			}
			else {
				EditText searchEditBox = (EditText) findViewById(R.id.brand_name);
				Intent mapSearch = new Intent(this, MapResultsActivity.class);
				mapSearch.putExtra("searchTerms", String.valueOf(searchEditBox.getText()));
				startActivity(mapSearch);
			}
		}
		
		// View all venues nearby
		else if (v.getId() == R.id.search_by_zip_button) {
			if(UserLocation.getInstance().isLocationValid() == false){
				Toast.makeText(this, "Invalid location, please select a custom location to continue.", Toast.LENGTH_SHORT).show();
			}
			else {
				Intent mapExplore = new Intent(this, MapResultsActivity.class);
				mapExplore.putExtra("searchTerms", "");
				startActivity(mapExplore);
				
			}
		}
		
		// change location (load ZIP screen)
		else if (v.getId() == R.id.change_location) {
			Intent searchByZipIntent = new Intent(this, SearchByZipActivity.class);
			startActivity(searchByZipIntent);
		}
	}

}