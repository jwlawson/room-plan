package uk.co.jwlawson.plan.activities;

import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

/**
 * Activity to show any options and preferences.
 * 
 * @author John Lawson
 * 
 */
public class OptionsActivity extends SherlockPreferenceActivity {
	
	private static final String TAG = "OptionsActivity";
	private static final boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
