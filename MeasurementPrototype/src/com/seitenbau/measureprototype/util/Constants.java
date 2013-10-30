package com.seitenbau.measureprototype.util;

import java.io.File;

import android.os.Environment;

public class Constants {
	
	// TAG for the MainActivity
	public static final String TAG_MAIN = "MainActivity";
	
	// TAG for the WifiActivity
	public static final String TAG_WIFI = "WifiActivity";

	// Path for measuring points
	public static final String ABSOLUTE_PATH = new File(Environment.getExternalStorageDirectory()
			+ File.separator + "Messungen").getAbsolutePath();

}
