package edu.umn.pinkpanthers.beerfinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import edu.umn.pinkpanthers.beerfinder.R;
import edu.umn.pinkpanthers.beerfinder.data.UserLocation;

/**
 * Displays a list of brands to select.
 */
public class SearchByZipActivity extends Activity implements OnClickListener {

	public static final String SELECTED_ZIP = "SELECTED_ZIP";
	private EditText enterZipInput;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_by_zip_screen);
		enterZipInput = (EditText) findViewById(R.id.enter_zip_button);
		findViewById(R.id.use_current_zip_button).setOnClickListener(this);
		
		enterZipInput.setText(UserLocation.getInstance().getZip());
	}

	// Handle clicking on the button(s)
	public void onClick(View v) {
		if (v.getId() == R.id.use_current_zip_button) {
			UserLocation.getInstance().updateLocation(String.valueOf(enterZipInput.getText()));
			finish();
		} 
	}

	// Back button
	public void homeClicked(View view) {
		finish();
	}
}
