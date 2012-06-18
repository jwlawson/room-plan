package uk.co.jwlawson.plan.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;

import uk.co.jwlawson.plan.R;
import uk.co.jwlawson.plan.views.DimView;

import java.util.ArrayList;

/**
 * Activity class allowing the user to input the dimensions of the room, given
 * its rough shape.
 * 
 * @author John Lawson
 * 
 */
public class DimViewActivity extends SherlockActivity {

	private static final String TAG = "DimViewActivity";
	private static final boolean DEBUG = false;

	private ArrayList<PointF> mPointList;
	private DimView mDimView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dim_view_layout);

		Intent intent = getIntent();

		if (!intent.hasExtra("point_list")) {
			Log.w(TAG, "No room and point data included");
			finish();
		}

		mPointList = intent.getParcelableArrayListExtra("point_list");

		mDimView = (DimView) findViewById(R.id.dimView1);

		mDimView.setPoints(mPointList);
	}
}
