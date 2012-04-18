package edu.umn.pinkpanthers.beerfinder.activities;

import android.os.Bundle;
import android.view.View;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import edu.umn.pinkpanthers.beerfinder.R;

public class MapResultsActivity extends MapActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.map_results_screen);
        mapView = (MapView) findViewById(R.id.map_results_map_view);
        mapView.setBuiltInZoomControls(true);
    }

    public void homeClicked(View view) {
        finish();
    }

    public void listResultsClicked(View view) {
        // TODO
        finish();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
