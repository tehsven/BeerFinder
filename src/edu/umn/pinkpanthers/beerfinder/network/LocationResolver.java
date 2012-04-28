package edu.umn.pinkpanthers.beerfinder.network;

import java.util.ArrayList;
import java.util.List;
import com.google.android.maps.GeoPoint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

public class LocationResolver {

	private static final String PROVIDER_NAME = "LocationResolver";

	private static Location DEFAULT_LOCATION;

	public static void getCurrentLocation(final Activity activity, final Callback<Location> callback) {
		// Acquire a reference to the system Location Manager
		final LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		final LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				locationManager.removeUpdates(this);
				callback.onSuccess(location);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// Request updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

		// Wait 10 seconds before bailing
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				locationManager.removeUpdates(locationListener);
				callback.onSuccess(getDefaultLocation());
			}
		}, 10000);
	}

	public static Location getDefaultLocation() {
		if (DEFAULT_LOCATION == null) {
			DEFAULT_LOCATION = new Location(PROVIDER_NAME);
			DEFAULT_LOCATION.setLatitude(44.976092);
			DEFAULT_LOCATION.setLongitude(-93.232212);
		}
		return DEFAULT_LOCATION;
	}

	public static List<Location> generateLocationsNear(Location location, int numLocations) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		List<Location> nearLocations = new ArrayList<Location>();
		for (int i = 0; i < numLocations; i++) {
			Location nearLocation = new Location(PROVIDER_NAME);
			nearLocation.setLatitude(latitude + ((Math.random() - 0.5) * 0.06));
			nearLocation.setLongitude(longitude + ((Math.random() - 0.5) * 0.06));
			nearLocations.add(nearLocation);
		}
		return nearLocations;
	}

	public static GeoPoint makeGeoPoint(Location defaultLocation) {
		return new GeoPoint((int) (defaultLocation.getLatitude() * 1000000),
				(int) (defaultLocation.getLongitude() * 1000000));
	}

}
