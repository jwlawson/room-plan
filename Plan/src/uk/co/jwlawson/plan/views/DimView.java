package uk.co.jwlawson.plan.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Class to draw the rough room shape and allow dimensions to be added.
 * 
 * @author John Lawson
 * 
 */
public class DimView extends View {
	
	private static final String TAG = "DimView";
	private static final boolean DEBUG = false;
	
	public DimView(Context context) {
		super(context);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
