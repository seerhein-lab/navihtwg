package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class WifiMeasuringPoint extends MeasuringPoint {

	
	private final String HEADLINE = "location" + Constants.SEPERATOR + "orientation"
			+ Constants.SEPERATOR + "date" + Constants.SEPERATOR + "time"
			+ Constants.SEPERATOR + "SSID" + Constants.SEPERATOR + "BBSID"
			+ Constants.SEPERATOR + "frequency (MHz)" + Constants.SEPERATOR
			+ "level (dBm)" + Constants.NEWLINE;
	private String SSID;
	private String BSSID;
	private int frequency;
	private int level;
	
	public WifiMeasuringPoint(MeasuringPosition location, String orientation,
			File file, DatePicker date, String sSID,
			String bSSID, int frequency, int level) {
		super(location, orientation, file, date);
		this.SSID = sSID;
		this.BSSID = bSSID;
		this.frequency = frequency;
		this.level = level;
		
	}

	@Override
	public String getHeadline() {
		return HEADLINE;
	}

	@Override
	public String getWritableData() {
		String writableData = getLocation().getID() + Constants.SEPERATOR
				+ getOrientation() + Constants.SEPERATOR + getDate()
				+ Constants.SEPERATOR + getTime() + Constants.SEPERATOR
				+ SSID + Constants.SEPERATOR
				+ BSSID + Constants.SEPERATOR
				+ frequency + Constants.SEPERATOR
				+ level + Constants.NEWLINE;
		return writableData;
	}

}
