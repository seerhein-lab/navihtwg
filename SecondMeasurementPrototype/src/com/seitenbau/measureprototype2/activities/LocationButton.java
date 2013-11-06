package com.seitenbau.measureprototype2.activities;

import com.seitenbau.measureprototype2.util.MeasuringPosition;

import android.content.Context;
import android.widget.Button;

public class LocationButton extends Button {
	
	private MeasuringPosition location;
	
	public LocationButton(Context context, MeasuringPosition location) {
		super(context);
		this.location = location;
		// TODO Auto-generated constructor stub
	}
	public MeasuringPosition getLocation() {
		return location;
	}
	public void setOnClickListener(OnClickListener onClickListener,
			MainActivity mainActivity) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
