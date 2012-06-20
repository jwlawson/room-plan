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
package uk.co.jwlawson.plan.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import uk.co.jwlawson.plan.VersionedGestureDetector;
import uk.co.jwlawson.plan.entities.Line;

import java.util.ArrayList;

/**
 * Class to draw the rough room shape and allow dimensions to be added.
 * 
 * @author John Lawson
 * 
 */
public class DimView extends View {

	private static final String TAG = "DimView";
	private static final boolean DEBUG = true;

	/** ArrayList of the points that make up the rough shape of the room */
	private ArrayList<PointF> mPointList;

	private final ArrayList<Line> mLineList;

	private final Paint mLinePaint;

	/** GestureDetector to handle all touch events */
	private final VersionedGestureDetector mDetector;

	/** Scale factor for drawing */
	private float mScaleFactor;

	/** x-coordinate for centre of scaling */
	private float mScaleX;

	/** y-coordinate for centre of scaling */
	private float mScaleY;

	public DimView(Context context) {
		this(context, null, 0);
	}

	public DimView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DimView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mLinePaint = new Paint();
		mLinePaint.setColor(Color.BLUE);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setStrokeWidth(2);
		mLinePaint.setStyle(Style.STROKE);

		mPointList = new ArrayList<PointF>();
		mLineList = new ArrayList<Line>();

		mDetector = VersionedGestureDetector.newInstance(context, new GestureHandler());
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}

	public void setPoints(ArrayList<PointF> list) {
		if (DEBUG) Log.d(TAG, "Points set");

		mPointList = list;
		for (int i = 0; i < mPointList.size(); i++) {
			PointF start = mPointList.get(i);
			PointF end;
			if (i == mPointList.size() - 1) {
				end = mPointList.get(0);
			} else {
				end = mPointList.get(i + 1);
			}

			Line line = new Line(start, end, mLinePaint);
			if (DEBUG) Log.d(TAG, "New line added " + line.toString());
			mLineList.add(line);
		}
	}

	/**
	 * Transforms the input to the same scale as the shapes
	 * 
	 * @param x
	 *            x-coordinate to transform
	 * @return new x-coordinate
	 */
	private float scaledX(float x) {
		return ((x - mScaleX) / mScaleFactor) + mScaleX;
	}

	/**
	 * Transforms the input to the same scale as the shapes
	 * 
	 * @param y
	 *            y-coordinate to transform
	 * @return new y-coordinate
	 */
	private float scaledY(float y) {
		return ((y - mScaleY) / mScaleFactor) + mScaleY;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();
		canvas.scale(mScaleFactor, mScaleFactor, mScaleX, mScaleY);
		// Draw stuff
		canvas.drawColor(Color.RED);
		for (Line line : mLineList) {
			line.draw(canvas);
		}
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return mDetector.onTouchEvent(ev);
	}

	private class GestureHandler implements VersionedGestureDetector.OnGestureListener {

		@Override
		public void onDown(float x, float y) {

		}

		@Override
		public void onDrag(float dx, float dy, float rawX, float rawY) {

		}

		@Override
		public void onScale(float scaleFactor, float x, float y) {
			mScaleFactor *= scaleFactor;

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
			mScaleX = x;
			mScaleY = y;

			invalidate();
		}

		@Override
		public void onDoubleTap(float x, float y) {

		}

	}
}
