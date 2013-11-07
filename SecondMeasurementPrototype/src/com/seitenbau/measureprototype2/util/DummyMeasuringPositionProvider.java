package com.seitenbau.measureprototype2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a map of test measuring position for the case that the json file is not available.
 */
public class DummyMeasuringPositionProvider {

	public static Map<Integer, MeasuringPosition> getLocationMap() {

		Map<Integer, MeasuringPosition> locationMap = new HashMap<Integer, MeasuringPosition>();
		
		MeasuringPosition testMeasuringPosition1 = new MeasuringPosition(1, "Test Position 1", "1st Floor");
		MeasuringPosition testMeasuringPosition2 = new MeasuringPosition(2, "Test Position 2", "1st Floor");
		
		locationMap.put(testMeasuringPosition1.getID(), testMeasuringPosition1);
		locationMap.put(testMeasuringPosition2.getID(), testMeasuringPosition2);
		
		
		return locationMap;
	}
}
