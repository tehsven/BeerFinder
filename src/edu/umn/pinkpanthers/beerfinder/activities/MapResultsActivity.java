package edu.umn.pinkpanthers.beerfinder.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
//import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import edu.umn.pinkpanthers.beerfinder.data.SearchResults;
import edu.umn.pinkpanthers.beerfinder.data.UserLocation;
import edu.umn.pinkpanthers.beerfinder.data.Venue;
import edu.umn.pinkpanthers.beerfinder.network.BeerFinderWebService;
import edu.umn.pinkpanthers.beerfinder.network.Callback;
//import edu.umn.pinkpanthers.beerfinder.network.LocationResolver;

public class MapResultsActivity extends MapActivity implements Callback <SearchResults>{

	private MapView mapView;
	private Venue venuesDisplayed[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_results_screen);
		mapView = (MapView) findViewById(R.id.map_results_view);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);

		//Location defaultLocation = LocationResolver.getDefaultLocation();
		//GeoPoint defaultPoint = LocationResolver.makeGeoPoint(defaultLocation);
		GeoPoint defaultPoint = UserLocation.getInstance().getGPS();
		MapController mc = mapView.getController();
		mc.setCenter(defaultPoint);
		mc.setZoom(14);

		// extract search terms.
		String searchTerms = getIntent().getExtras().getString("searchTerms");
		
		// Perform asynchronous search.
		BeerFinderWebService.getInstance().performServerSearch(
			UserLocation.getInstance().getGPS(), 
			100, 
			0, 
			searchTerms, 
			this
		);
		
		//createOverlays();
	}

	public void homeClicked(View view) {
		finish();
	}

	public void listResultsClicked(View view) {
		Intent listResultsIntent = new Intent(this, ListResultsActivity.class);
		ArrayList<Venue> v = new ArrayList <Venue> (Arrays.asList(venuesDisplayed));
		listResultsIntent.putParcelableArrayListExtra(Venue.SELECTED_VENUE, v);
		startActivity(listResultsIntent);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void createOverlays(SearchResults data) {
		// create the itemized overlay
		Drawable drawable = this.getResources().getDrawable(android.R.drawable.btn_star_big_on);
		VenueItemizedOverlay venueOverlay = new VenueItemizedOverlay(this, drawable);
		mapView.getOverlays().add(venueOverlay);

		venuesDisplayed = data.venue;
		
		// Add an overlay for each Venue
		for (Venue venue : venuesDisplayed) {
			GeoPoint venuePoint = venue.getLocation();
			OverlayItem venueItem = new OverlayItem(venuePoint, venue.getName(), venue.getAddress());
			venueOverlay.addOverlay(venueItem);
		}
		
		mapView.invalidate();
	}

	private class VenueItemizedOverlay extends ItemizedOverlay<OverlayItem> {

		private ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
		private Context _context;

		public VenueItemizedOverlay(Context context, Drawable drawable) {
			super(boundCenterBottom(drawable));
			_context = context;
		}

		@Override
		protected OverlayItem createItem(int i) {
			return overlays.get(i);
		}

		@Override
		public int size() {
			return overlays.size();
		}

		public void addOverlay(OverlayItem overlay) {
			overlays.add(overlay);
			populate();
		}

		// Handle click venue event.
		@Override
		protected boolean onTap(int index) {
			OverlayItem venueItem = overlays.get(index);
			if (venueItem != null) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(_context);
				dialog.setTitle(venueItem.getTitle());
				dialog.setMessage(venueItem.getSnippet());
				dialog.show();
				return true;
			} else {
				return false;
			}

		}

	}

	@Override
	public void onSuccess(final SearchResults value) {
		if(value != null){
			runOnUiThread(new Runnable() {
			     public void run() {
			    	createOverlays(value);
			    }
			});
		}
	}

	@Override
	public void onFailure(SearchResults value) {
		// TODO Auto-generated method stub
		
	}

}
