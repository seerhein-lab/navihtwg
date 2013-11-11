package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class PressureMeasuringPoint extends MeasuringPoint {

	private final String HEADLINE = "location" + Constants.SEPERATOR + "orientation"
			+ Constants.SEPERATOR + "date" + Constants.SEPERATOR + "time"
			+ Constants.SEPERATOR + "Pressure (hPa / millibar)" 
			+ Constants.NEWLINE;
	private float pressure;
	
	
	
	
	public PressureMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, float pressure) {
		super(location, orientation, file, date);
		this.pressure = pressure;
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
				+ Float.toString(getPressure())
				+ Constants.NEWLINE;
		writableData = writableData.replace(".", ",");
		return writableData;
	}

	public float getPressure() {
		return pressure;
	}
	
	

}
