package com.seitenbau.measureprototype.activities;

import java.io.File;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.seitenbau.measureprototype.util.JSONutil;
import com.seitenbau.measureprototype.util.Location;

public class MainActivity extends Activity {

	// JSON File containing the array of Locations to measure 
	private static final File JSONFILE = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getAbsolutePath()+"/test.json");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		JSONutil jsonUtil = new JSONutil();
//		File file = new File(JSONFILE);
		Map<Integer, Location> locationMap = jsonUtil.jsonToLocation(JSONFILE);
		addLocationButtons(locationMap);
	}

	private void addLocationButtons(Map<Integer, Location> locationMap) {

		for (Integer key : locationMap.keySet()) {
			final LocationButton b = new LocationButton(
					getApplicationContext(), locationMap.get(key));

			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			b.setText("Pos  " + b.getLocation().getID() + " - " 
					+ b.getLocation().getFloor() + " " 
					+ b.getLocation().getDesc());
			b.setTextColor(Color.BLACK);
			b.setLayoutParams(params);
			b.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(),
							b.getLocation().getDesc(), Toast.LENGTH_SHORT)
							.show();
					startMeasurement(v, b.getLocation());
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
	
	public void startMeasurement(View view, Location loc) {
		Intent intent = new Intent(this, OrientationActivity.class);
		intent.putExtra(loc.getClass().getName(), loc);
		startActivity(intent);
	}

}
