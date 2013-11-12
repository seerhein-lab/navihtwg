package com.seitenbau.measureprototype2.activities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seitenbau.measureprototype2.data.MeasuringPoint;
import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.DatePicker;
import com.seitenbau.measureprototype2.util.MeasuringPosition;
import com.sensorlib.OurSensorCollector;

public class OrientationActivity extends Activity {

	private String point[] = { "A", "B", "C", "D", "E", "F", "G", "H" };

	private DatePicker datePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		MeasuringPosition loc = (MeasuringPosition) bundle
				.get(MeasuringPosition.class.getName());
		TextView textView = (TextView) findViewById(R.id.headLineOrientation);
		textView.setText(loc.getID() + " - " + loc.getFloor() + " - "
				+ loc.getDesc());
		addPoints(loc);
	}

	private void addPoints(final MeasuringPosition loc) {
		for (int i = 0; i < point.length; i++) {
			final int dummy = i;
			final OrientationButton b = new OrientationButton(this, point[i],
					loc);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			b.setText("Ausrichtung: " + b.getOrientation());
			b.setTextColor(Color.BLACK);
			b.setLayoutParams(params);
			b.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					final OurSensorCollector sensorCollector;
//					final SensorCollector sensorCollector;
					try {
						
						sensorCollector = new OurSensorCollector(
								getApplicationContext(), 
								new File(Constants.ABSOLUTE_PATH + "/"),
								point[dummy],
								loc);
						
//						sensorCollector = new SensorCollector(getApplicationContext(), new File(Constants.ABSOLUTE_PATH + "/sensors.txt"));
						
						final Runnable startCollectingDataRunnable = new Runnable() {
							@Override
							public void run() {
								sensorCollector.start();
							}
						};
						startCollectingDataRunnable.run();
						
						
					
						b.setText("collecting");
						final Timer timer = new Timer();
				        final Handler handler = new Handler();
				        final Runnable stopCollectingDataRunnable = new Runnable() {
				            @Override
				            public void run() {
				            	sensorCollector.close();
				            }
						};
						TimerTask task = new TimerTask() {
				            @Override
				            public void run() {
				            	handler.post(stopCollectingDataRunnable);

				    			
				            }
				        };
		
				        timer.schedule(task, 2000); 
						
						
						b.setVisibility(View.GONE);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Error",
								Toast.LENGTH_SHORT).show();
					} 

				}
			});

			LinearLayout ll = (LinearLayout) findViewById(R.id.the_layout);
			ll.addView(b);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void wifiScan(final MeasuringPoint mp) {
		mp.getFile().mkdir();

		final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		if (wifiManager.isWifiEnabled() == false) {
			Toast.makeText(getApplicationContext(), "Wifi ist deaktiviert",
					Toast.LENGTH_SHORT).show();
			try {
				wifiManager.setWifiEnabled(true);
				Toast.makeText(getApplicationContext(),
						"Wifi wird aktiviert. . .", Toast.LENGTH_SHORT).show();
				Log.d(Constants.TAG_WIFI, "Wifi aktiviert");
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"Fehler beim aktivieren. . .", Toast.LENGTH_SHORT)
						.show();
				Log.e(Constants.TAG_WIFI,
						"Fehler beim aktivieren des Wifis Moduls");
			}
		}

		BroadcastReceiver wifiReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				StringBuilder stringBuilder = new StringBuilder();
				List<ScanResult> scanResults = wifiManager.getScanResults();
				stringBuilder.append("Messung------------\n");

				for (int i = 0; i < scanResults.size(); i++) {
					stringBuilder.append(mp.getOrientation());
					stringBuilder.append(";");
					stringBuilder.append(datePicker.getDate());
					stringBuilder.append(";");
					stringBuilder.append(datePicker.getTime());
					stringBuilder.append(";");
					stringBuilder.append(scanResults.get(i).SSID.toString());
					stringBuilder.append(";");
					stringBuilder.append(scanResults.get(i).BSSID.toString());
					stringBuilder.append(";");
					stringBuilder.append(scanResults.get(i).frequency);
					stringBuilder.append(";");
					stringBuilder.append(scanResults.get(i).level);
					stringBuilder.append("\n");
					stringBuilder.append("------------------------\n");
				}
				Toast.makeText(getApplicationContext(), "Messung ist da",
						Toast.LENGTH_SHORT).show();
				unregisterReceiver(this);

				mp.saveTofile();
			}
		};

		// wifiReceiver = new WifiReceiver();
		datePicker = new DatePicker();

		registerReceiver(wifiReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		// wifiManager.startScan();

	}

	// class WifiReceiver extends BroadcastReceiver {
	// @Override
	// public void onReceive(Context ctx, Intent intent) {
	// stringBuilder = new StringBuilder();
	// scanResults = wifiManager.getScanResults();
	//
	// for (int i = 0; i < scanResults.size(); i++) {
	// stringBuilder.append(datePicker.getDate());
	// stringBuilder.append(";");
	// stringBuilder.append(datePicker.getTime());
	// stringBuilder.append(";");
	// stringBuilder.append(scanResults.get(i).SSID.toString());
	// stringBuilder.append(";");
	// stringBuilder.append(scanResults.get(i).BSSID.toString());
	// stringBuilder.append(";");
	// // stringBuilder.append(scanResults.get(i).timestamp);
	// // stringBuilder.append(";");
	// stringBuilder.append(scanResults.get(i).frequency);
	// stringBuilder.append(";");
	// stringBuilder.append(scanResults.get(i).level);
	// stringBuilder.append("\n");
	// }
	// }
	// }

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	


}
