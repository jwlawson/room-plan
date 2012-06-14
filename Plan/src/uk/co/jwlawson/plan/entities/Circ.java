package uk.co.jwlawson.plan.entities;

import android.util.Log;

/**
 * Circle class. Represents any circular item.
 * 
 * @author John Lawson
 * 
 */
public class Circ extends Furniture {
	
	private static final String TAG = "Circ";
	private static final boolean DEBUG = false;
	
	public Circ(String name) {
		super(name);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
