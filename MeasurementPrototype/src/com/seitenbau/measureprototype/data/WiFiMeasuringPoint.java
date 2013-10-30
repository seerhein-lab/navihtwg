package com.seitenbau.measureprototype.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.util.Log;

import com.seitenbau.measureprototype.util.Constants;
import com.seitenbau.measureprototype.util.Location;


public class WiFiMeasuringPoint extends MeasuringPoint {
	

	public WiFiMeasuringPoint(Location location, String orientation,
			File file) {
		super(location, orientation, file);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveTofile(StringBuilder stringBuilder) {
		File file = new File(getFile().getAbsolutePath() + "/WifiScan.csv");
		BufferedWriter writer = null;
		if (!file.exists()) {
			try {
				file.createNewFile();
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(stringBuilder.toString());
				writer.flush();
			} catch (IOException e) {
//				Toast.makeText(getApplicationContext(),
//						"Fehler beim Anlegen der Datei aufgetreten",
//						Toast.LENGTH_SHORT).show();
				Log.e(Constants.TAG_WIFI, "Fehler beim anlegen der Datei");
			}
		} else {
			try {
				FileWriter fw = new FileWriter(file, true);
				fw.append(stringBuilder.toString());
				fw.flush();
				fw.close();
//				Toast.makeText(getApplicationContext(),
//						"Neue Messwerte wurden hinzugefügt",
//						Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
//				Toast.makeText(getApplicationContext(),
//						"Fehler beim einfügen in vorhandene Datei",
//						Toast.LENGTH_SHORT).show();
				Log.e(Constants.TAG_WIFI, "Fehler beim einfügen in vorhandene Datei");
			}
		}
	}
}
