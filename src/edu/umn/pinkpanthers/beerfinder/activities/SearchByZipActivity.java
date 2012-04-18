package edu.umn.pinkpanthers.beerfinder.activities;

import java.io.IOException;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Beer;

/**
 * Displays a list of brands to select.
 */
public class SearchByZipActivity extends Activity implements OnClickListener {

    public static final String SELECTED_ZIP = "SELECTED_ZIP";
    private EditText enterZipInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        enterZipInput = (EditText) findViewById(R.id.enter_zip_button);
        findViewById(R.id.use_current_zip_button).setOnClickListener(this);
        findViewById(R.id.search_by_zip_go_button).setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.use_current_zip_button) {
            getCurrentZip();
        } else if (v.getId() == R.id.search_by_zip_go_button) {
            startMapResultsActivity(enterZipInput.getText().toString());
        }
    }
    
    private void getCurrentZip() {
        // Show a spinner while the zip code is retrieved
        final ProgressDialog spinner = ProgressDialog.show(this, "Working..", "Getting current zip...", true, false);

        // Acquire a reference to the system Location Manager
        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                List<Address> addresses = null;
                try {
                    addresses = new Geocoder(SearchByZipActivity.this).getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                    if (addresses != null && addresses.size() >= 1) {
                        Address firstAddress = addresses.get(0);
                        String zipCode = firstAddress.getPostalCode();
                        enterZipInput.setText(zipCode);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    locationManager.removeUpdates(this);
                    spinner.dismiss();
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }
    
    private void startMapResultsActivity(String zip) {
         Intent mapResultsIntent = new Intent(getApplicationContext(), MapResultsActivity.class);
         mapResultsIntent.putExtra(SearchByZipActivity.SELECTED_ZIP, zip);
         startActivity(mapResultsIntent);
    }
}
