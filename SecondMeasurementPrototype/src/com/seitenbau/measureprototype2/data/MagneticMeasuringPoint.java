package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class MagneticMeasuringPoint extends MeasuringPoint {

	private final String HEADLINE = "location" + Constants.SEPERATOR
			+ "orientation" + Constants.SEPERATOR + "date"
			+ Constants.SEPERATOR + "time" + Constants.SEPERATOR
			+ "device name" + Constants.SEPERATOR + "device id"
			+ Constants.SEPERATOR + "x" + Constants.SEPERATOR + "y"
			+ Constants.SEPERATOR + "z" + Constants.SEPERATOR
			+ "magnetic field strength" + Constants.NEWLINE;
	private float x;
	private float y;
	private float z;
	private String device;

	public MagneticMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, float x, float y,
			float z, String device) {
		super(location, orientation, file, date);
		this.x = x;
		this.y = y;
		this.z = z;
		this.device = device;

	}

	@Override
	public String getWritableData() {
		String writableData = getLocation().getID() + Constants.SEPERATOR
				+ getOrientation() + Constants.SEPERATOR + getDate()
				+ Constants.SEPERATOR + getTime() + Constants.SEPERATOR
				+ device + Constants.SEPERATOR + Float.toString(getX())
				+ Constants.SEPERATOR + Float.toString(getY())
				+ Constants.SEPERATOR + Float.toString(getZ())
				+ Constants.SEPERATOR + Double.toString(getAbsoluteStrength())
				+ Constants.NEWLINE;
		writableData = writableData.replace(".", ",");
		return writableData;
	}

	@Override
	public String getHeadline() {
		return HEADLINE;
	}

	private double getAbsoluteStrength() {
		double magneticFieldStrength;
		magneticFieldStrength = Math.sqrt(getX() * getX() + getY() * getY()
				+ getZ() * getZ());
		return magneticFieldStrength;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

}
