package uk.co.jwlawson.plan.entities;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Container class to hold information about the room. The room itself is a collection of lines, which can be drawn on a canvas.
 * 
 * @author John Lawson
 * 
 */
public class Room {
	
	private final static String TAG = "Room";
	private static final boolean DEBUG = false;
	
	private ArrayList<Line> mLineList;
	
	public Room() {
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	public void draw(Canvas canvas) {
		for (Line line : mLineList) {
			line.draw(canvas);
		}
	}
}
