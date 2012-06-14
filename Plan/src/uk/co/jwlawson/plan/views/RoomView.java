package uk.co.jwlawson.plan.views;

import java.util.ArrayList;

import uk.co.jwlawson.plan.VersionedGestureDetector;
import uk.co.jwlawson.plan.entities.Room;
import uk.co.jwlawson.plan.entities.Shape;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * View class to hold and draw the room and any furniture in it.
 * 
 * @author John Lawson
 * 
 */
public class RoomView extends View {
	
	private static final String TAG = "RoomView";
	private static final boolean DEBUG = false;
	
	/** Handles touch events on this view. */
	private VersionedGestureDetector mGestureDetector;
	
	/** List of all furniture in this room */
	private ArrayList<Shape> mShapeList;
	
	/** Room that contains the furniture */
	private Room mRoom;
	
	/** Index of currently selected piece of furniture */
	private int mSelectedItem;
	
	/** Scale factor for drawing */
	private float mScaleFactor;
	
	/** x-coordinate for centre of scaling */
	private float mScaleX;
	
	/** y-coordinate for centre of scaling */
	private float mScaleY;
	
	public RoomView(Context context) {
		this(context, null, 0);
	}
	
	public RoomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public RoomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mShapeList = new ArrayList<Shape>();
		
		mGestureDetector = VersionedGestureDetector.newInstance(context, new GestureHandler());
		
		mScaleFactor = 1.f;
		mScaleX = 0f;
		mScaleY = 0f;
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	public void setRoom(Room room) {
		mRoom = room;
	}
	
	public void addShape(Shape shape) {
		mShapeList.add(shape);
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
		
		if (mRoom != null) mRoom.draw(canvas);
		
		for (Shape item : mShapeList) {
			item.draw(canvas);
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
			mSelectedItem = -1;
			
			for (int i = 0; i < mShapeList.size(); i++) {
				if (mShapeList.get(i).contains(scaledX(x), scaledY(y))) {
					mSelectedItem = i;
					return;
				}
			}
		}
		
		@Override
		public void onDrag(float dx, float dy, float rawX, float rawY) {
			if (mSelectedItem >= 0 && mSelectedItem < mShapeList.size()) {
				mShapeList.get(mSelectedItem).offset(dx / mScaleFactor, dy / mScaleFactor);
				invalidate();
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
