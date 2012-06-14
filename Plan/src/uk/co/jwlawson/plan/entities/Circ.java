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
	
	private PointF mPoint;
	private float mRad;
	
	private Paint mPaint;
	
	public Circ(float x, float y, float radius, Paint paint, String name) {
		super(name);
		
		mPoint = new PointF(x, y);
		mRad = radius;
		mPaint = paint;
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	public void setPaint(Paint paint) {
		mPaint = paint;
	}
	
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
	}
}
