package com.seitenbau.measureprototype.activities;

import com.seitenbau.measureprototype.util.Location;

import android.content.Context;
import android.widget.Button;

public class LocationButton extends Button {
	
	private Location location;
	
	public LocationButton(Context context, Location location) {
		super(context);
		this.location = location;
		// TODO Auto-generated constructor stub
	}
	public Location getLocation() {
		return location;
	}
	public void setOnClickListener(OnClickListener onClickListener,
			MainActivity mainActivity) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
