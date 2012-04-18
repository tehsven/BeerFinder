package edu.umn.pinkpanthers.beerfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import edu.umn.pinkpanthers.beerfinder.R;

public class MapResultsActivity extends MapActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_results_screen);
        mapView = (MapView) findViewById(R.id.map_results_map_view);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);

        long latitude = 83;
        long longitude = 133;
        GeoPoint p = new GeoPoint((int) (latitude * 1000000), (int) (longitude * 1000000));
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
