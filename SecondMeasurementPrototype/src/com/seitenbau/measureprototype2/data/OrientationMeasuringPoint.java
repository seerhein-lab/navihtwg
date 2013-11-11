package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class OrientationMeasuringPoint extends MeasuringPoint {

	private final String HEADLINE = "location" + Constants.SEPERATOR + "orientation"
			+ Constants.SEPERATOR + "date" + Constants.SEPERATOR + "time"
			+ Constants.SEPERATOR + "Azimuth (Z) deg" + Constants.SEPERATOR + "Pitch (X) deg"
			+ Constants.SEPERATOR + "Roll (Y) deg" + Constants.SEPERATOR
			+ "magnetic field strength" + Constants.NEWLINE;
	
	private float azimuth_z;
	private float pitch_x;
	private float roll_y;
	
	


	public OrientationMeasuringPoint(MeasuringPosition location,
			String orientation, File file, DatePicker date, float azimuth_z,
			float pitch_x, float roll_y) {
		super(location, orientation, file, date);
		this.azimuth_z = azimuth_z;
		this.pitch_x = pitch_x;
		this.roll_y = roll_y;
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
				+ Float.toString(getAzimuth_z()) + Constants.SEPERATOR
				+ Float.toString(getPitch_x()) + Constants.SEPERATOR
				+ Float.toString(getRoll_y()) + Constants.SEPERATOR
				+ Constants.NEWLINE;
		writableData = writableData.replace(".", ",");
		return writableData;
	}

	public float getAzimuth_z() {
		return azimuth_z;
	}

	public float getPitch_x() {
		return pitch_x;
	}

	public float getRoll_y() {
		return roll_y;
	}
	
	

}
