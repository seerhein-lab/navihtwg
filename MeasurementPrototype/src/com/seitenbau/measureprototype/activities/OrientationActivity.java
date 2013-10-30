package com.seitenbau.measureprototype.activities;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seitenbau.measureprototype.data.MeasuringPoint;
import com.seitenbau.measureprototype.data.WiFiMeasuringPoint;
import com.seitenbau.measureprototype.util.Constants;
import com.seitenbau.measureprototype.util.DatePicker;
import com.seitenbau.measureprototype.util.Location;

public class OrientationActivity extends Activity {

	private String point[] = { "A", "B", "C", "D", "E", "F", "G", "H" };
	private WifiManager wifiManager;
	private List<ScanResult> scanResults;
	private StringBuilder stringBuilder;

	private DatePicker datePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		Location loc = (Location) bundle.get(Location.class.getName());
		TextView textView = (TextView) findViewById(R.id.headLineOrientation);
		textView.setText(loc.getID() + " - " + loc.getFloor() + " - "
				+ loc.getDesc());
		// Toast.makeText(getApplicationContext(), "Loc "+ loc.getDesc(),
		// Toast.LENGTH_SHORT).show();
		addPoints(loc);
	}

	private void addPoints(final Location loc) {
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
					wifiScan(new WiFiMeasuringPoint(loc, point[dummy], new File(Constants.ABSOLUTE_PATH + "/M" + loc.getID() + "/" )));
				
				
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

		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

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
				stringBuilder = new StringBuilder();
				scanResults = wifiManager.getScanResults();

				for (int i = 0; i < scanResults.size(); i++) {
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
				}
				mp.saveTofile(stringBuilder);
			}
		};
		
//		wifiReceiver = new WifiReceiver();
		datePicker = new DatePicker();

		registerReceiver(wifiReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		wifiManager.startScan();
		
	}

//	class WifiReceiver extends BroadcastReceiver {
//		@Override
//		public void onReceive(Context ctx, Intent intent) {
//			stringBuilder = new StringBuilder();
//			scanResults = wifiManager.getScanResults();
//
//			for (int i = 0; i < scanResults.size(); i++) {
//				stringBuilder.append(datePicker.getDate());
//				stringBuilder.append(";");
//				stringBuilder.append(datePicker.getTime());
//				stringBuilder.append(";");
//				stringBuilder.append(scanResults.get(i).SSID.toString());
//				stringBuilder.append(";");
//				stringBuilder.append(scanResults.get(i).BSSID.toString());
//				stringBuilder.append(";");
//				// stringBuilder.append(scanResults.get(i).timestamp);
//				// stringBuilder.append(";");
//				stringBuilder.append(scanResults.get(i).frequency);
//				stringBuilder.append(";");
//				stringBuilder.append(scanResults.get(i).level);
//				stringBuilder.append("\n");
//			}
//		}
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
