package com.seitenbau.measureprototype2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONutil {

	public static final String ID = "id";
	public static final String DESC = "desc";
	public static final String ENTRY = "entry";
	public static final String FLOOR = "floor";
	
	

	public Map<Integer, MeasuringPosition> jsonToLocation(File f) {

		Map<Integer, MeasuringPosition> locationMap = new HashMap<Integer, MeasuringPosition>();
		JSONParser parser = new JSONParser();
		File file = f;
		Object obj;
		
		try {
			obj = parser.parse(new FileReader(file));
		
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray array = (JSONArray) jsonObject.get(ENTRY);
	
			Iterator<?> it = array.iterator();
			while (it.hasNext()) {
				
				JSONObject job = (JSONObject) it.next();
//---------------------WTF???----------------------------------------				
//				long id = (long) job.get("id");
//---------------------WTF???----------------------------------------
				long id = ((Long)job.get(ID)).longValue();
				
				String desc = (String) job.get(DESC);
				String floor = (String) job.get(FLOOR);

				locationMap.put((int)id, new MeasuringPosition((int)id, desc, floor));

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Integer key : locationMap.keySet()) {
			System.out.println("Key: " + key);
			System.out.println("Value: " +  locationMap.get(key).getDesc());
		}
		
		
		return locationMap;

	}

}
