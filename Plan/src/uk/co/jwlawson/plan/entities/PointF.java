package uk.co.jwlawson.plan.entities;

import android.graphics.Point;

public class PointF extends android.graphics.PointF {

	@Override
	public String toString() {
		return "PointF ( " + x + " , " + y + " )";
	}

	public PointF() {
		super();
	}

	public PointF(float x, float y) {
		super(x, y);
	}

	public PointF(Point p) {
		super(p);
	}
}
