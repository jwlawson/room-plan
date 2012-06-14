package uk.co.jwlawson.plan.activities;

import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockListActivity;

/**
 * List activity of all rooms stored on device.
 * 
 * @author John Lawson
 * 
 */
public class RoomList extends SherlockListActivity {
	
	private static final String TAG = "RoomListActivity";
	private static final boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
}
