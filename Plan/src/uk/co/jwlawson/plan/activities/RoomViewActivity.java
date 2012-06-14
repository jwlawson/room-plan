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
package uk.co.jwlawson.plan.activities;

import uk.co.jwlawson.plan.R;
import uk.co.jwlawson.plan.entities.Circ;
import uk.co.jwlawson.plan.views.RoomView;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Holds a RoomView instance and provides any buttons etc required for it.
 * 
 * @author John Lawson
 * 
 */
public class RoomViewActivity extends SherlockActivity {
	
	private static final String TAG = "RoomViewActivity";
	private static final boolean DEBUG = true;
	
	private RoomView mRoomView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.room_view_layout);
		
		mRoomView = (RoomView) findViewById(R.id.roomView);
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setTextSize(25);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		mRoomView.addShape(new Circ(0, 0, 100, paint, "one"));
		mRoomView.addShape(new Circ(100, 150, 50, paint, "two"));
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		Log.d(TAG, "home id: " + android.R.id.home);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("back").setIcon(R.drawable.ic_menu_back).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("new").setIcon(R.drawable.ic_menu_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("options").setIcon(R.drawable.ic_menu_settings_holo_light).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG) Log.d(TAG, "Menu selection: " + item.getTitle());
		/* android.R.id.home: is the ItemId for the actionbar home button. */
		
		return false;
	}
	
}
