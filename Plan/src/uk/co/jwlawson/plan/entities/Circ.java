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
import android.graphics.PointF;
import android.util.Log;

/**
 * Circle class. Represents any circular item.
 * 
 * @author John Lawson
 * 
 */
public class Circ extends Shape {
	
	private static final String TAG = "Circ";
	private static final boolean DEBUG = false;
	
	private final PointF mPoint;
	private final float mRad;
	
	private Paint mPaint;
	
	public Circ(float x, float y, float radius, Paint paint, String name) {
		super(name);
		
		mPoint = new PointF(x, y);
		mRad = radius;
		mPaint = paint;
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	public float getX() {
		return mPoint.x;
	}
	
	public float getY() {
		return mPoint.y;
	}
	
	@Override
	public void setPaint(Paint paint) {
		mPaint = paint;
	}
	
	@Override
	public void setColour(int colour) {
		mPaint.setColor(colour);
	}
	
	@Override
	public boolean contains(float x, float y) {
		double dx = Math.pow(mPoint.x - x, 2);
		double dy = Math.pow(mPoint.y - y, 2);
		
		return (dx + dy < Math.pow(mRad, 2));
	}
	
	@Override
	public void offset(float x, float y) {
		mPoint.offset(x, y);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(mPoint.x, mPoint.y, mRad, mPaint);
		canvas.drawText(mName, mPoint.x, mPoint.y + (mPaint.getTextSize() * 7 / 20), mPaint);
	}
}
