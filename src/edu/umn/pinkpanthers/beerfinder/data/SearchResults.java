package edu.umn.pinkpanthers.beerfinder.data;

import com.google.android.maps.GeoPoint;

/**
 * "Structure class for searchResults.
 * 
 */
public class SearchResults {

	public final GeoPoint searchLocation;
	public final String searchString;
	public final int count;
	public final int offset;
	public final Venue venue[];

	/**
	 * Structure Constructor
	 */
	public SearchResults(
			GeoPoint location,
			String terms,
			int totalNumberOfSearchResults,
			int offsetIntoResultsList,
			Venue locations[]
    ){
		searchLocation = location;
		searchString = terms;
		count = totalNumberOfSearchResults;
		offset = offsetIntoResultsList;
		venue = locations;
	}
}
