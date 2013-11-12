package com.seitenbau.measureprototype2.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class WeatherDataService {

	private static final String TAG = "WeatherDataService";

	private static WeatherDataService instance;

	private HttpClient httpclient;
	private HttpGet httpGet;

	private static Date lastUpdate;
	private static Float airPressure;
	private static boolean activeRequest=false;

	public static WeatherDataService getInstance() {
		if (instance == null)
			instance = new WeatherDataService();
		return instance;
	}

	public WeatherDataService() {
		httpclient = new DefaultHttpClient();
		httpGet = new HttpGet(
				"http://api.worldweatheronline.com/free/v1/weather.ashx?q=47.6704575%2C9.1554939&format=csv&num_of_days=1&fx=no&includelocation=no&show_comments=no&key=3473mu25ygjphcx96924dh27");

	}
	
	private class GetDataTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... params) {
	    	try {
				// Execute HTTP Post Request

				HttpResponse response = httpclient.execute(httpGet);

				// response.getEntity().consumeContent();
				// InputStream in = response.getEntity().getContent();
				// String encoding = "UTF-8";
				// String body = IOUtils.toString(in, encoding);

				String responseBody = EntityUtils
						.toString(response.getEntity());
				String regex = ".*,(\\d*),\\d*";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(responseBody);
				if (matcher.find() && matcher.groupCount() == 1) {
					String airPressureString = matcher.group(1);
					airPressure = Float.valueOf(airPressureString);
					lastUpdate = new Date();
				}
				activeRequest = false;
				
			
				
				
			// } catch (ClientProtocolException e) {
			// // nothing
			} catch (IOException e) {
				// nothing
			}

	    	return "OK";
	  }
	}

	

	public Float getAirPressure() {
		
		Calendar pressureExpiryTime = Calendar.getInstance();
		//refresh data every 10 minutes
		pressureExpiryTime.add(Calendar.MINUTE, -10);
		if ((lastUpdate==null || lastUpdate.before(pressureExpiryTime.getTime()))&&(!activeRequest)){
			activeRequest = true;
			Log.i(TAG, "Retrieving new weather data");
			GetDataTask task = new GetDataTask();
			task.execute(new String[] {});
			 
		}		
		return airPressure;
	}

}
