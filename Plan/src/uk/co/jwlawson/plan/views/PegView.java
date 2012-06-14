package uk.co.jwlawson.plan.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Draws a grid of pegs. Touch input used to form room shape. Room is then passed on to DimView.
 * 
 * @author John Lawson
 * 
 */
public class PegView extends View {
	
	private static final String TAG = "PegView";
	private static final boolean DEBUG = false;
	
	public PegView(Context context) {
		super(context);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
