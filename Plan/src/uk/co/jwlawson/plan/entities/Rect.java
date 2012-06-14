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
import android.graphics.RectF;
import android.util.Log;

/**
 * rectangle class. Represents any rectangle or square item.
 * 
 * @author John Lawson
 * 
 */
public class Rect extends Shape {
	
	private static final String TAG = "Rect";
	private static final boolean DEBUG = false;
	
	private RectF mRect;
	private Paint mPaint;
	
	public Rect(float left, float top, float right, float bottom, Paint paint, String name) {
		super(name);
		
		mRect = new RectF(left, top, right, bottom);
		mPaint = paint;
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	@Override
	public boolean contains(float x, float y) {
		return mRect.contains(x, y);
	}
	
	@Override
	public void offset(float x, float y) {
		mRect.offset(x, y);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(mRect, mPaint);
	}
	
	@Override
	public void setPaint(Paint paint) {
		mPaint = paint;
	}
	
	@Override
	public void setColour(int colour) {
		mPaint.setColor(colour);
	}
	
}
