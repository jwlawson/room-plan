package uk.co.jwlawson.plan.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class Line {
	
	private final static String TAG = "Line";
	private static final boolean DEBUG = false;
	
	private PointF start;
	private PointF end;
	private Paint mPaint;
	
	public Line(float x1, float y1, float x2, float y2, Paint paint) {
		start = new PointF(x1, y1);
		end = new PointF(x2, y2);
		
		mPaint = paint;
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	public void draw(Canvas canvas) {
		canvas.drawLine(start.x, start.y, end.x, end.y, mPaint);
	}
	
	public PointF getStart() {
		return start;
	}
	
	public PointF getEnd() {
		return end;
	}
	
	public void reverse() {
		PointF tmp = start;
		start = end;
		end = tmp;
	}
}
