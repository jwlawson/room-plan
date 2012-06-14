/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package uk.co.jwlawson.plan.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Abstract class representing a piece of furniture. Is drawn on a canvas, can be moved etc.
 * 
 * @author John Lawson
 * 
 */
public abstract class Shape {
	
	private static final String TAG = "Furniture";
	private static final boolean DEBUG = false;
	
	protected String mName;
	
	public Shape(String name) {
		mName = name;
		
		if (DEBUG) Log.d(TAG, "New furniture created " + name);
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public abstract boolean contains(float x, float y);
	
	public abstract void offset(float x, float y);
	
	public abstract void draw(Canvas canvas);
	
	public abstract void setPaint(Paint paint);
	
	public abstract void setColour(int colour);
	
}
