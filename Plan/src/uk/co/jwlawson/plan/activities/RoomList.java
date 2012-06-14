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

import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockListActivity;

/**
 * List activity of all rooms stored on device.
 * 
 * @author John Lawson
 * 
 */
public class RoomList extends SherlockListActivity {
	
	private static final String TAG = "RoomListActivity";
	private static final boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
}
