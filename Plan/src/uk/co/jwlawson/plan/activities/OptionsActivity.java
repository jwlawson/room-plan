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

import com.actionbarsherlock.app.SherlockPreferenceActivity;

/**
 * Activity to show any options and preferences.
 * 
 * @author John Lawson
 * 
 */
public class OptionsActivity extends SherlockPreferenceActivity {
	
	private static final String TAG = "OptionsActivity";
	private static final boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
