package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class LocationMeasuringPoint extends MeasuringPoint {

	private final String HEADLINE = "location" + Constants.SEPERATOR
			+ "orientation" + Constants.SEPERATOR + "date"
			+ Constants.SEPERATOR + "time" + Constants.SEPERATOR + "latitude"
			+ Constants.SEPERATOR + "longitude" + Constants.SEPERATOR
			+ "altitude" + Constants.SEPERATOR + "accuracy" + Constants.NEWLINE;

	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	private String linkToMaps = "https://maps.google.de/maps?q=";

	public LocationMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, double latitude,
			double longitude, double altitude, float accuracy) {
		super(location, orientation, file, date);
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;

	}

	@Override
	public String getWritableData() {
		String writableData = getLocation().getID() + Constants.SEPERATOR
				+ getOrientation() + Constants.SEPERATOR + getDate()
				+ Constants.SEPERATOR + getTime() + Constants.SEPERATOR
				+ Double.toString(getLatitude()) + Constants.SEPERATOR
				+ Double.toString(getLongitude()) + Constants.SEPERATOR
				+ Double.toString(getAltitude()) + Constants.SEPERATOR
				+ Float.toString(getAccuracy()) + Constants.SEPERATOR;
		
		
		writableData = writableData.replace(".", ",");
		writableData += linkToMaps + Double.toString(getLatitude()) +"," 
			+ Double.toString(getLongitude())
			+ Constants.NEWLINE;
		
		return writableData;
	}

	@Override
	public String getHeadline() {
		return HEADLINE;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public float getAccuracy() {
		return accuracy;
	}

}
