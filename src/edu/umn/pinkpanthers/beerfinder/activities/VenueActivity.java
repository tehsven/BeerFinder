package edu.umn.pinkpanthers.beerfinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.Venue;

public class VenueActivity extends Activity {

    private TextView nameView;
    private TextView addressView;
    private TextView phoneNumber;
    private Venue venue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        venue = getIntent().getExtras().getParcelable(Venue.SELECTED_VENUE);

        this.setContentView(R.layout.venue_screen);

        nameView = (TextView) findViewById(R.id.venue_toolbar_title);
        addressView = (TextView) findViewById(R.id.venue_address_label);
        phoneNumber = (TextView) findViewById(R.id.venue_phone_number_label);

        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO
    }

    private void updateView() {
        nameView.setText(venue.getName());
        addressView.setText(venue.getAddress());
        phoneNumber.setText(venue.getPhoneNumber());
    }

    public void homeClicked(View view) {
        finish();
    }

}
