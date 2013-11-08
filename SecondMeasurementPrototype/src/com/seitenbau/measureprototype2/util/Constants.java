package com.seitenbau.measureprototype2.util;

import java.io.File;

import android.os.Environment;

public class Constants {
	
	// Separator
	public static final String SEPERATOR = ";";
	
	// Newline
	public static final String NEWLINE = "\n";
	
	public static final String EXTENSION = ".csv";
	
	public static final String WIFIFILE = "wifi_scan";
	
	// TAG for the MainActivity
	public static final String TAG_MAIN = "MainActivity";
	
	// TAG for the WifiActivity
	public static final String TAG_WIFI = "WifiActivity";

	// Path for collected sensor data (measuring points)
	public static final String ABSOLUTE_PATH = new File(Environment.getExternalStorageDirectory()
			+ File.separator + "MessungenPT2").getAbsolutePath();

}
