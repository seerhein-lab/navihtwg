package com.seitenbau.measureprototype.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatePicker {
	
	private Date date = new Date();
	// Set the DateFormat e.g. (2013.10.29)
	private String dateFormat = "yyyy.MM.dd";
	private SimpleDateFormat getDate = new SimpleDateFormat(dateFormat,
			Locale.GERMANY);
	private String getTime = SimpleDateFormat.getTimeInstance(
			SimpleDateFormat.MEDIUM, Locale.GERMANY).format(date);

	/**
	 * 
	 * @return the actual Date (yyyy.MM.dd).
	 */
	public String getDate() {
		return getDate.format(date);
	}

	/**
	 * 
	 * @return the actual Time (hh:mm:ss).
	 */
	public String getTime() {
		return getTime;
	}
}
