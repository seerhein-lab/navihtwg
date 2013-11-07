package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class LocationMeasuringPoint extends MeasuringPoint {


	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	
	private String orientation;
	private MeasuringPosition location;

	public LocationMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, double latitude, double longitude,double altitude,
			float accuracy) {
		super(location, orientation, file, date);
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
		this.orientation = orientation;
		this.location = location;

	}

	@Override
	public String getWritableData() {
		String writableData = location.getDesc() + Constants.SEPERATOR
				+ orientation + Constants.SEPERATOR + getDate()
				+ Constants.SEPERATOR + getTime() + Constants.SEPERATOR
				+ Double.toString(getLatitude()) + Constants.SEPERATOR
				+ Double.toString(getLongitude()) + Constants.SEPERATOR
				+ Double.toString(getAltitude()) + Constants.SEPERATOR
				+ Float.toString(getAccuracy()) + Constants.NEWLINE;
		writableData = writableData.replace(".", ",");
		return writableData;
	}

	@Override
	public String getHeadline() {
		return "location;orientation;date;time;latitude;longitude;altitude;accuracy"
				+ Constants.NEWLINE;
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
