package com.seitenbau.measureprototype.data;

import java.io.File;

import com.seitenbau.measureprototype.util.Location;

public abstract class MeasuringPoint {

	private Location location;
	private String orientation;
	private File file;

	public MeasuringPoint(Location location, String orientation, File file) {
		super();
		this.location = location;
		this.orientation = orientation;
		this.file = file;
	}

	public abstract void saveTofile(StringBuilder stringBuilder);

	public Location getLocation() {
		return location;
	}

	public String getOrientation() {
		return orientation;
	}

	public File getFile() {
		return file;
	}
	
}
