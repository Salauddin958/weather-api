package com.epam.weatherapi.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.epam.weatherapi.utils.CommonUtils;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeather;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;

@Service
public class WeatherService {
	
	public final static String API_TOKEN = "f7bf4276f55cef31534b1186065563b2";
	OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);
	public String getDailyWeatherInfo(String location) {
		
		final String weatherJson = openWeatherClient
		        .currentWeather()
		        .single()
		        .byCityName(location)
		        .language(Language.ENGLISH)
		        .unitSystem(UnitSystem.METRIC)
		        .retrieve()
		        .asJSON();
		return weatherJson;
	}
	
	public String getHistoricWeatherInfo(String location) {
		final HistoricalWeatherData historicalWeatherData = openWeatherClient
		        .oneCall()
		        .historical()
		        .byCoordinateAndTimestamp(CommonUtils.getCoordinate(location), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
		        .language(Language.ENGLISH)
		        .unitSystem(UnitSystem.METRIC)
		        .retrieve()
		        .asJava();
		StringBuilder sb = new StringBuilder();
		HistoricalWeather weather = historicalWeatherData.getHistoricalWeather();
		sb.append("Temperature : ").append(weather.getTemperature());
		sb.append(" Clouds : ").append(weather.getClouds());
		sb.append(" Humidity : ").append(weather.getHumidity());
		sb.append(" Rain : ").append(weather.getRain());
		sb.append(" Atmospheric Pressure : ").append(weather.getAtmosphericPressure());
		sb.append(" Wind : ").append(weather.getWind());
		return sb.toString();
	}
	
	public String getFutureWeatherDetails(String location) {
		final Forecast forecast = openWeatherClient
		        .forecast5Day3HourStep()
		        .byCityName(location)
		        .language(Language.ENGLISH)
		        .unitSystem(UnitSystem.METRIC)
		        .count(15) 
		        .retrieve()
		        .asJava();
		return ""+forecast.getWeatherForecasts();
	}
	
}
