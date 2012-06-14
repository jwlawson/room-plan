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

import java.util.ArrayList;

import uk.co.jwlawson.plan.R;
import uk.co.jwlawson.plan.entities.Line;
import uk.co.jwlawson.plan.views.PegView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

/** Activity to show and manage the peg board room creation. */
public class PegViewActivity extends SherlockActivity {
	
	private static final String TAG = "PegViewActivity";
	private static final boolean DEBUG = true;
	
	private PegView mPegView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.peg_view_layout);
		
		mPegView = (PegView) findViewById(R.id.pegView1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inf = getSupportMenuInflater();
		inf.inflate(R.menu.peg_view_act_menu, menu);
		if (DEBUG) Log.d(TAG, "Menu inflated");
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.peg_view_menu_done) {
			done();
			if (DEBUG) Log.d(TAG, "Menu item DONE selected");
		}
		return true;
	}
	
	private void done() {
		ArrayList<Line> lineList = mPegView.getLineList();
		
		if (lineList.isEmpty()) {
			Toast.makeText(getApplicationContext(), "No walls added", Toast.LENGTH_SHORT).show();
			return;
		}
		
		ArrayList<Line> result = new ArrayList<Line>();
		
		Line l1 = lineList.get(0);
		Line initLine = l1;
		// result.add(l1);
		
		do {
			for (Line l2 : lineList) {
				if (l2.getStart().equals(l1.getEnd())) {
					result.add(l2);
					lineList.remove(l2);
					l1 = l2;
					break;
				}
				if (l2.getEnd().equals(l1.getEnd())) {
					l2.reverse();
					result.add(l2);
					lineList.remove(l2);
					l1 = l2;
					break;
				}
				Toast.makeText(getApplicationContext(), "No walls added", Toast.LENGTH_SHORT).show();
				return;
			}
		} while (l1 != initLine && !lineList.isEmpty());
		
		if (l1 != initLine) {
			Toast.makeText(getApplicationContext(), "Room shape not valid", Toast.LENGTH_SHORT).show();
		}
	}
}
