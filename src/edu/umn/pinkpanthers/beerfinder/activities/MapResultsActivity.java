package edu.umn.pinkpanthers.beerfinder.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.network.LocationResolver;

public class MapResultsActivity extends MapActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_results_screen);
        mapView = (MapView) findViewById(R.id.map_results_view);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(false);

        Location location = LocationResolver.getDefaultLocation();
        GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1000000), (int) (location.getLongitude() * 1000000));
        MapController mc = mapView.getController();
        mc.setCenter(p);
        mc.setZoom(14);
    }

    public void homeClicked(View view) {
        finish();
    }

    public void listResultsClicked(View view) {
        Intent listResultsIntent = new Intent(this, ListResultsActivity.class);
        startActivity(listResultsIntent);
        finish();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
