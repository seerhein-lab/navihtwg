package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class MagneticMeasuringPoint extends MeasuringPoint {

	private String headline = "x;y;z;Magnetfeldstärke" + Constants.NEWLINE;
	private float x;
	private float y;
	private float z;

	public MagneticMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, float x, float y,
			float z) {
		super(location, orientation, file, date);
		this.x = x;
		this.y = y;
		this.z = z;

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

	@Override
	public String getWritableData() {
		String writableData = Float.toString(getX()) + Constants.SEPERATOR
				+ Float.toString(getY()) + Constants.SEPERATOR
				+ Float.toString(getZ()) + Constants.SEPERATOR
				+ Double.toString(getAbsoluteStrength()) + Constants.NEWLINE;
		writableData = writableData.replace(".", ",");
		return writableData;
	}

	@Override
	public String getHeadline() {
		return headline;
	}

	public double getAbsoluteStrength() {
		double magneticFieldStrength;
		magneticFieldStrength = Math.sqrt(getX() * getX() + getY() * getY()
				+ getZ() * getZ());
		return magneticFieldStrength;
	}

}
