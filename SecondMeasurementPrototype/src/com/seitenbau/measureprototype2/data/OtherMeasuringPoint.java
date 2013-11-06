package com.seitenbau.measureprototype2.data;

import java.io.File;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public class OtherMeasuringPoint extends MeasuringPoint{
	
	private String headline = "othersensors" + Constants.NEWLINE;
	private String data;

	public OtherMeasuringPoint(MeasuringPosition location, String orientation,
			File file, DatePicker date, String data) {
		super(location, orientation, file, date);
		this.data = data;
	}

	public String getData() {
		return data;
	}

	@Override
	public String getHeadline() {
			return headline;
	}

	@Override
	public String getWritableData() {
		String writableData = getData();
		return writableData;
	}

}
