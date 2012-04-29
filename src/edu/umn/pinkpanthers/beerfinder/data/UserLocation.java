package edu.umn.pinkpanthers.beerfinder.data;

import java.util.Collections;

import com.google.android.maps.GeoPoint;

import edu.umn.pinkpanthers.beerfinder.network.BeerFinderWebService;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Model class for a Beer.
 * 
 */
public class UserLocation {

	private String zipcode;	
	private static UserLocation instance;
	
	/** 
	 * Private 'Singleton' constructor
	 */
	private UserLocation() {
		zipcode = "";
		
		// TODO - ask device for GPS location. if found, set zip to "55455" (U of M)
		//      - else, force user to enter a zip code.
	}

	/**
	 * Create the 'Singleton' object.
	 */
	public static void initialize() {
		if (instance == null) {
			instance = new UserLocation();
		}
	}

	/**
	 * 'Singleton' accessor method.
	 */
	public static UserLocation getInstance() {
		if (instance == null) {
			throw new RuntimeException("Cannot access UserLocation! Call initialize() first!");
		}
		return instance;
	}
	
	public boolean isLocationValid(){
		if(zipcode != null && zipcode.length() == 5)
			return true;
		else
			return false;
	}
	
	// only update location if it is a five digit number.
	// otherwise set location invalid
	public void updateLocation(String zip){
		Log.d("UserLocation", "Updated: " + zip);
		if(zip == null || zip.length() != 5){
			zipcode = "";
		}
		else{
			try{
				Integer.parseInt(zip);
				zipcode = zip;
			}
			catch(NumberFormatException e){
				zipcode = "";
			}
		}
	}
	
	public String getZip(){
		return zipcode;
	}
	
	public GeoPoint getGPS(){
		// ideally, the GPS would tell us directly the current location
		// or, we would use the ZIP code to determine the GPS location
		// instead, hard-code a location so the search results are always displayed
		return new GeoPoint((int)(1000000 * 44.976092),(int)(1000000 *  -93.232212)); // hard code a location.
	}
	
	
	
}
