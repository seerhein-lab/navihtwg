package com.seitenbau.measureprototype.activities;

import com.seitenbau.measureprototype.util.Location;

import android.content.Context;
import android.widget.Button;

public class OrientationButton extends Button {
	
	private String orientation;
	private Location location;
	
	public OrientationButton(Context context, String orientation, Location location) {
		super(context);
		this.location = location;
		this.orientation = orientation;
		
	}
	public Location getLocation() {
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
