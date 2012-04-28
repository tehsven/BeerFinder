package edu.umn.pinkpanthers.beerfinder.activities;

import java.util.List;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import edu.umn.pinkpanthers.beerfinder.network.BeerFinderWebService;

/**
 * Displays a list of beers to select.
 */
public class SearchByBeerActivity extends ListActivity {

	private BeerAdapter beerAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_by_brand_screen);
		initListView();
	}

	@Override
	public void onResume() {
		super.onResume();
		beerAdapter.setList(getSearchableBeerList());
		beerAdapter.notifyDataSetChanged();
	}

	private void initListView() {
		if (beerAdapter == null) {
			beerAdapter = new BeerAdapter(this, R.layout.search_by_brand_item, getSearchableBeerList());
			setListAdapter(beerAdapter);
		}
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SearchByBeerActivity.this.startMapResultsActivity(((BeerAdapter) getListAdapter()).getItem(position));
			}

		});
	}

	private List<Beer> getSearchableBeerList() {
		List<Beer> searchableBeers = BeerFinderWebService.getInstance().getSearchableBeerList();
		return searchableBeers;
	}

	public class BeerAdapter extends ArrayAdapter<Beer> {

		List<Beer> beers;

		public BeerAdapter(Context context, int textViewResourceId, List<Beer> beers) {
			super(context, textViewResourceId, beers);
			this.beers = beers;
		}

		public void setList(List<Beer> beerList) {
			beers = beerList;
			clear();
			for (Beer beer : beerList) {
				add(beer);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Beer beer = getItem(position);
			View item = getLayoutInflater().inflate(R.layout.search_by_brand_item, parent, false);
			((TextView) item.findViewById(R.id.search_by_brand_item_name)).setText(beer.getName());
			((TextView) item.findViewById(R.id.search_by_brand_item_brewery)).setText(beer.getBreweryName());

			return item;
		}
	}

	public void homeClicked(View view) {
		finish();
	}

	private void startMapResultsActivity(Beer selectedBeer) {
		Intent mapResultsIntent = new Intent(getApplicationContext(), MapResultsActivity.class);
		mapResultsIntent.putExtra(Beer.SELECTED_BEER, selectedBeer);
		startActivity(mapResultsIntent);
	}

}
