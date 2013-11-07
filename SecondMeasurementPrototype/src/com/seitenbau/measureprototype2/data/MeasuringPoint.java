package com.seitenbau.measureprototype2.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.util.Log;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;

public abstract class MeasuringPoint {

	private MeasuringPosition location;
	private String orientation;
	private File file;
	private DatePicker date;
	

	public MeasuringPoint(MeasuringPosition location, String orientation,
			File file, DatePicker date) {
		super();
		this.location = location;
		this.orientation = orientation;
		this.file = file;
		this.date = date;
		
	}

		
	public void saveTofile() {
//		file.mkdirs();
		BufferedWriter writer = null;
		if (!file.exists()) {
			try {
				//create subdirectories if necessary
				File parent = file.getParentFile();
				if(!parent.exists() && !parent.mkdirs()){
				    throw new IllegalStateException("Couldn't create dir: " + parent);
				}
				file.createNewFile();
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(getHeadline());
				writer.write(getWritableData());
				writer.flush();
			} catch (IOException e) {
				Log.e(Constants.TAG_WIFI, "Fehler beim anlegen der Datei");
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				
				writer = new BufferedWriter(new FileWriter(file, true));
				writer.write(getWritableData());
				writer.flush();
//				FileWriter fw = new FileWriter(file, true);
//				fw.append(getWritableData());
//				fw.flush();
//				fw.close();
			} catch (IOException e) {
				Log.e(Constants.TAG_WIFI,
						"Fehler beim einf�gen in vorhandene Datei");
			} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}


	public MeasuringPosition getLocation() {
		return location;
	}

	public String getOrientation() {
		return orientation;
	}

	public File getFile() {
		return file;
	}


	public String getDate() {
		date = new DatePicker();
		return date.getDate();
	}
	
	public String getTime() {
		date = new DatePicker();
		return date.getTime();
	}
	

	public abstract String getHeadline();

	public abstract String getWritableData(); 

}
