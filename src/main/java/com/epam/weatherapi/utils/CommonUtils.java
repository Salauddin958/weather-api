package com.epam.weatherapi.utils;

import com.github.prominence.openweathermap.api.model.Coordinate;

public class CommonUtils {

	public static Coordinate getCoordinate(String location) {
		
		// London, New York, Minsk, Hyderabad
		if("London".equalsIgnoreCase(location))
			return Coordinate.of(51.507351, -0.127758);
		else if("New York".equalsIgnoreCase(location))
			return Coordinate.of(40.712776, -74.005974);
		else if("Minsk".equalsIgnoreCase(location))
			return Coordinate.of(53.904541, 27.561523);
		else
			return Coordinate.of(17.3850, 78.4867);
	}
}
