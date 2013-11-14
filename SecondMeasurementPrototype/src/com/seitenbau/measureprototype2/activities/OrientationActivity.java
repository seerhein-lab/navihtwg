package com.seitenbau.measureprototype2.activities;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seitenbau.measureprototype2.util.Constants;
import com.seitenbau.measureprototype2.util.MeasuringPosition;
import com.sensorlib.OurSensorCollector;

public class OrientationActivity extends Activity {

	private String point[] = { "A", "B", "C", "D", "E", "F", "G", "H" };

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
					try {
						
						sensorCollector = new OurSensorCollector(
								getApplicationContext(), 
								new File(Constants.ABSOLUTE_PATH + "/"),
								point[dummy],
								loc);
						
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
						
				        Toast.makeText(getApplicationContext(), "Messung gespeichert",
								Toast.LENGTH_SHORT).show();
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	


}
