package com.seitenbau.measureprototype.util;

import java.io.Serializable;

public class Location implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6268178495653994989L;

	
	private int ID;
	private String desc;
	private String floor;
	
	public Location(int iD, String desc, String floor) {
		this.ID = iD;
		this.desc = desc;
		this.floor = floor;
	}

	public int getID() {
		return ID;
	}

	public String getDesc() {
		return desc;
	}

	public String getFloor() {
		return floor;
	}
	
	
	
	
}
