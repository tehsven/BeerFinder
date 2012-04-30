package edu.umn.pinkpanthers.beerfinder.activities;

//Toast.makeText(this.getContext(), "VenueAdapter: " + VenueList.size(), Toast.LENGTH_SHORT).show();

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Venue;


/**
 * Displays a list of Venues to select.
 */
public class ListResultsActivity extends ListActivity {

	private VenueAdapter venueAdapter;
	private VenueAdapter search_venueAdapter;

	//private Intent launchIntent;
	
	private List<Venue> fullVenueList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_results_screen);	// define XML file which contains screen display info
		fullVenueList = getIntent().getParcelableArrayListExtra(Venue.SELECTED_VENUE);
		initListView();									// add initial venues to list
		handleIntent(getIntent());		
	}

	@Override
	public void onResume() {
		super.onResume();
		venueAdapter.setList(getSearchableVenueList());
		venueAdapter.notifyDataSetChanged();
	}

	private void initListView() {
		if (venueAdapter == null) {
			venueAdapter = new VenueAdapter(this, R.layout.list_results_item, getSearchableVenueList());
			setListAdapter(venueAdapter);
		}
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		// Touch listener to launch VenueActivity when a row is touched.
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListResultsActivity.this.startVenueActivity(((VenueAdapter) getListAdapter()).getItem(position));
			}
		});
	}

	// Return a copy of the 'master' venue list
	private List<Venue> getSearchableVenueList() {
		return new ArrayList<Venue>(fullVenueList);
	}
	
	public class VenueAdapter extends ArrayAdapter<Venue> {

		public VenueAdapter(Context context, int textViewResourceId, List<Venue> Venues) {
			super(context, textViewResourceId, Venues);
		}

		// remove the old rows and create new rows, one for
		// each item in the parameter list
		public void setList(List<Venue> VenueList) {
			clear();
			for (Venue venue : VenueList) {
				add(venue);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Venue venue = getItem(position);
			View item = getLayoutInflater().inflate(R.layout.list_results_item, parent, false);
			((TextView) item.findViewById(R.id.list_results_item_name)).setText(venue.getName());
			((TextView) item.findViewById(R.id.list_results_item_address)).setText(venue.getAddress());

			return item;
		}
	}

	// back button
	public void homeClicked(View view) {
		finish();
	}

	// Launch the VenueActivity Activity
	private void startVenueActivity(Venue selectedVenue) {
		Intent venueIntent = new Intent(getApplicationContext(), VenueActivity.class);
		venueIntent.putExtra(Venue.SELECTED_VENUE, selectedVenue);
		startActivity(venueIntent);
	}
	
	// TODO
	@Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }
	
	// TODO
	private void handleIntent(Intent intent) {		
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
          
            List<Venue> searchableVenues = this.getSearchableVenueList();//BeerFinderWebService.getInstance().getSearchableVenueList();
            List<Venue> venueSearchResults = new ArrayList<Venue>();
          
            for (int i = 0; i < searchableVenues.size(); i++)
            {
            	if (searchableVenues.get(i).getName().toLowerCase().contains(query.toLowerCase())){
            		venueSearchResults.add(searchableVenues.get(i));
        		}
            }
            //venueAdapter.setList(venueSearchResults);
            search_venueAdapter = new VenueAdapter(this, R.layout.list_results_item, venueSearchResults);
            setListAdapter(search_venueAdapter);
        }
	}
	
}
