package uk.co.jwlawson.plan.entities;

import android.util.Log;

/**
 * Abstract class representing a piece of furniture. Is drawn on a canvas, can be moved etc.
 * 
 * @author John Lawson
 * 
 */
public abstract class Furniture {
	
	private static final String TAG = "Furniture";
	private static final boolean DEBUG = false;
	
	protected String mName;
	
	public Furniture(String name) {
		mName = name;
		
		if (DEBUG) Log.d(TAG, "New furniture created " + name);
	}
	
}
