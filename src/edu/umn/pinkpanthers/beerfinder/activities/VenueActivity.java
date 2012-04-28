package edu.umn.pinkpanthers.beerfinder.activities;

import java.util.ArrayList;
import java.util.List;

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

public class VenueActivity extends Activity {

    private TextView nameView;
    private TextView addressView;
    private TextView phoneNumber;
    private Venue venue;

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
        // TODO
    }

    private void updateView() {
        nameView.setText(venue.getName());
        addressView.setText(venue.getAddress());
        phoneNumber.setText(venue.getPhoneNumber());

        createBeerList();
    }

    private void createBeerList() {
        // TODO use the beerIds in the Venue to get the actual beers that
        // correspond to this Venue
//        List<Beer> beers = BeerFinderWebService.getInstance().getSearchableBeerList();
        List<Beer> beers = new ArrayList<Beer>();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup beerViewGroup = (ViewGroup) findViewById(R.id.venue_beer_list_group);

        // get data for each of the beers from the "webserver"
        for(String beerID : venue.getBeers()){
        	Beer can = BeerFinderWebService.getInstance().getBeerInfoFromServer(beerID);
        	
        	if(can != null){
        		beers.add(can);	// add to list for later reference..
        	//	Toast.makeText(this, "Beer: " + can.getName() + "  " + can.getID(), Toast.LENGTH_SHORT).show();
        	}
        	// update display information
        	
        }
        
        //Toast.makeText(this, "Beer count: " + beers.size(), Toast.LENGTH_SHORT).show();
        
        // update display to show each of the beers
        for (Beer beer : beers) {
            View beerView = inflater.inflate(R.layout.venue_beer_list_item, null);
            beerView.setTag(beer);
            
            ((TextView) beerView.findViewById(R.id.venue_beer_list_item_name)).setText(beer.getName());
            ((TextView) beerView.findViewById(R.id.venue_beer_list_item_brewery)).setText(beer.getBreweryName());

            beerView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent beerIntent = new Intent(getApplicationContext(), BeerActivity.class);
                    beerIntent.putExtra(Beer.SELECTED_BEER, (Beer)view.getTag());
                    startActivity(beerIntent);
                }
            });
            
            beerViewGroup.addView(beerView);
        }
    }

    public void homeClicked(View view) {
        finish();
    }

}
