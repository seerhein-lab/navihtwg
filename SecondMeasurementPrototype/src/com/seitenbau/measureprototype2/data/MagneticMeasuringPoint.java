package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class MagneticMeasuringPoint extends MeasuringPoint {

	private String headline = "x;y;z" + Constants.NEWLINE;
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
		String writableData = getX() + Constants.SEPERATOR + getY()
				+ Constants.SEPERATOR + getZ() + Constants.NEWLINE;

		return writableData;
	}

	@Override
	public String getHeadline() {
		return headline;
	}

}
