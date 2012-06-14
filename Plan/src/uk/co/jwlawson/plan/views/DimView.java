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
package uk.co.jwlawson.plan.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Class to draw the rough room shape and allow dimensions to be added.
 * 
 * @author John Lawson
 * 
 */
public class DimView extends View {
	
	private static final String TAG = "DimView";
	private static final boolean DEBUG = false;
	
	public DimView(Context context) {
		super(context);
		
		if (DEBUG) Log.d(TAG, "New " + TAG + " created.");
	}
	
}
