package uk.co.jwlawson.plan.entities;

/**
 * Abstract class representing a piece of furniture. Is drawn on a canvas, can be moved etc.
 * 
 * @author John Lawson
 * 
 */
public abstract class Furniture {
	
	private static final String TAG = "Furniture";
	
	private String mName;
	
	public Furniture(String name) {
		mName = name;
	}
	
}
