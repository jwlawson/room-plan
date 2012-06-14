package uk.co.jwlawson.plan.activities;

import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;

/**
 * Holds a RoomView instance and provides any buttons etc required for it.
 * 
 * @author John Lawson
 * 
 */
public class RoomViewActivity extends SherlockActivity {
	
	private static final String TAG = "RoomViewActivity";
	private static final boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
