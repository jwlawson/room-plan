package uk.co.jwlawson.plan.entities;

import android.util.Log;

/**
 * rectangle class. Represents any rectangle or square item.
 * 
 * @author John Lawson
 * 
 */
public class Rect extends Furniture {
	
	private static final String TAG = "Rect";
	private static final boolean DEBUG = false;
	
	public Rect(String name) {
		super(name);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
