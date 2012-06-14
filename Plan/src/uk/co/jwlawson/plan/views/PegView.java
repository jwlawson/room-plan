package uk.co.jwlawson.plan.views;

import java.util.ArrayList;

import uk.co.jwlawson.plan.VersionedGestureDetector;
import uk.co.jwlawson.plan.entities.Circ;
import uk.co.jwlawson.plan.entities.Line;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Draws a grid of pegs. Touch input used to form room shape. Room is then passed on to DimView.
 * 
 * @author John Lawson
 * 
 */
public class PegView extends View {
	
	private static final String TAG = "PegView";
	private static final boolean DEBUG = true;
	
	/** Paint to pass to circles making up peg board */
	private Paint mPegPaint;
	
	/** Paint to pass to lines between pegs */
	private Paint mLinePaint;
	
	/** Paint to pass to catchment areas */
	private Paint mCatchPaint;
	
	/** Index of currently selected piece of peg */
	private int mSelectedPeg;
	
	/** Index of previously selected peg */
	private int mLastSelectedPeg;
	
	/** Handles touch events on this view. */
	private VersionedGestureDetector mGestureDetector;
	
	/** Scale factor for drawing */
	private float mScaleFactor;
	
	/** x-coordinate for centre of scaling */
	private float mScaleX;
	
	/** y-coordinate for centre of scaling */
	private float mScaleY;
	
	/** List of all lines making up the room */
	private ArrayList<Line> mLineList;
	
	/** List of all pegs in view */
	private ArrayList<Circ> mPegList;
	
	/** List of circles centred at points with larger radii */
	private ArrayList<Circ> mCatchList;
	
	public PegView(Context context) {
		this(context, null, 0);
	}
	
	public PegView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public PegView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mScaleFactor = 1.f;
		mScaleX = 0f;
		mScaleY = 0f;
		
		mPegPaint = new Paint();
		mPegPaint.setColor(Color.BLUE);
		mPegPaint.setAntiAlias(true);
		mPegPaint.setStyle(Style.STROKE);
		mPegPaint.setStrokeWidth(2);
		
		mCatchPaint = new Paint(mPegPaint);
		mCatchPaint.setAlpha(0x40);
		
		mLinePaint = new Paint(mPegPaint);
		mLinePaint.setColor(Color.GRAY);
		
		mGestureDetector = VersionedGestureDetector.newInstance(context, new GestureHandler());
		
		mLineList = new ArrayList<Line>();
		mPegList = new ArrayList<Circ>();
		mCatchList = new ArrayList<Circ>();
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	private void addLine(float x1, float y1, float x2, float y2) {
		mLineList.add(new Line(x1, y1, x2, y2, mLinePaint));
	}
	
	public ArrayList<Line> getLineList() {
		return mLineList;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		// TODO Handle rotation of screen
		
		mPegList = new ArrayList<Circ>();
		mCatchList = new ArrayList<Circ>();
		
		int min = (w < h ? w : h);
		float tmp = (float) min / 6;
		for (int i = 0; i <= w / tmp; i++) {
			for (int j = 0; j <= h / tmp; j++) {
				mPegList.add(new Circ(i * tmp, j * tmp, min / 40, mPegPaint, ""));
				mCatchList.add(new Circ(i * tmp, j * tmp, min / 15, mCatchPaint, ""));
			}
		}
	}
	
	/**
	 * Transforms the input to the same scale as the shapes
	 * 
	 * @param x x-coordinate to transform
	 * @return new x-coordinate
	 */
	private float scaledX(float x) {
		return ((x - mScaleX) / mScaleFactor) + mScaleX;
	}
	
	/**
	 * Transforms the input to the same scale as the shapes
	 * 
	 * @param y y-coordinate to transform
	 * @return new y-coordinate
	 */
	private float scaledY(float y) {
		return ((y - mScaleY) / mScaleFactor) + mScaleY;
	}
	
	/**
	 * Set the scale factor of the view
	 * 
	 * @param scale Scale factor to set
	 */
	public void setScale(float scale) {
		mScaleFactor = scale;
		mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
		mScaleX = getWidth() / 2;
		mScaleY = getHeight() / 2;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.save();
		canvas.scale(mScaleFactor, mScaleFactor, mScaleX, mScaleY);
		
		for (Circ circ : mCatchList) {
			circ.draw(canvas);
		}
		
		for (Circ circ : mPegList) {
			circ.draw(canvas);
		}
		
		for (Line line : mLineList) {
			line.draw(canvas);
		}
		canvas.restore();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return mGestureDetector.onTouchEvent(ev);
	}
	
	/** Handles any touch event on this view */
	private class GestureHandler implements VersionedGestureDetector.OnGestureListener {
		
		@Override
		public void onDown(float x, float y) {
			mSelectedPeg = -1;
			
			for (int i = 0; i < mCatchList.size(); i++) {
				if (mCatchList.get(i).contains(scaledX(x), scaledY(y))) {
					mSelectedPeg = i;
					return;
				}
			}
		}
		
		@Override
		public void onDrag(float dx, float dy, float rawX, float rawY) {
			if (mSelectedPeg >= 0 && mSelectedPeg < mPegList.size()) {
				for (int i = 0; i < mCatchList.size(); i++) {
					if (i == mSelectedPeg || i == mLastSelectedPeg) continue;
					
					Circ circ = mCatchList.get(i);
					if (circ.contains(scaledX(rawX), scaledY(rawY))) {
						addLine(mCatchList.get(mSelectedPeg).getX(), mCatchList.get(mSelectedPeg).getY(), circ.getX(), circ.getY());
						if (DEBUG) Log.d(TAG, "Dots joined");
						mLastSelectedPeg = mSelectedPeg;
						mSelectedPeg = i;
						invalidate();
						return;
					}
				}
			}
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
