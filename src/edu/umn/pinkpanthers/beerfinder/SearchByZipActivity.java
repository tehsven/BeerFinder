package edu.umn.pinkpanthers.beerfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Displays a list of brands to select.
 */
public class SearchByZipActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        findViewById(R.id.search_by_brand_button).setOnClickListener(this);
        findViewById(R.id.search_by_zip_button).setOnClickListener(this);
        findViewById(R.id.search_by_zip_go_button).setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.search_by_brand_button) {
            // TODO
        }
        else if (v.getId() == R.id.search_by_zip_button) {
            // TODO
        }
        else if (v.getId() == R.id.search_by_zip_go_button) {
            // TODO
//            Intent mapResultsIntent = new Intent(this, MapResultsActivity.class);
//            startActivity(mapResultsIntent);
        }
    }

}
