package com.seitenbau.measureprototype2.activities;

import com.seitenbau.measureprototype2.util.MeasuringPosition;

import android.content.Context;
import android.widget.Button;

public class OrientationButton extends Button {
	
	private String orientation;
	private MeasuringPosition location;
	
	public OrientationButton(Context context, String orientation, MeasuringPosition location) {
		super(context);
		this.location = location;
		this.orientation = orientation;
		
	}
	public MeasuringPosition getLocation() {
		return location;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOnClickListener(OnClickListener onClickListener,
			MainActivity mainActivity) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
