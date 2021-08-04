package com.epam.weatherapi.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.weatherapi.utils.CommonUtils;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeather;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;
import com.github.prominence.openweathermap.api.model.weather.Weather;

@Service
public class WeatherService {
	
	public final static String API_TOKEN = "f7bf4276f55cef31534b1186065563b2";
	OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);
	public String getDailyWeatherInfo(String location) {
		
		final Weather weather = openWeatherClient
		        .currentWeather()
		        .single()
		        .byCityName(location)
		        .language(Language.ENGLISH)
		        .unitSystem(UnitSystem.METRIC)
		        .retrieve()
		        .asJava();
		StringBuilder sb = new StringBuilder();
		sb.append(weather.getTemperature()+"\n");
		sb.append(System.getProperty("line.separator"));
		sb.append(weather.getHumidity()+"\n");
		sb.append(System.getProperty("line.separator"));
		sb.append(weather.getAtmosphericPressure()+"\n");
		sb.append(System.getProperty("line.separator"));
		sb.append(weather.getWind()+"\n");
		sb.append(System.getProperty("line.separator"));
		sb.append(weather.getClouds()+"\n");
		return sb.toString();
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
		sb.append(weather.getTemperature()+"\n");
		sb.append(weather.getHumidity()+"\n");
		sb.append(weather.getAtmosphericPressure()+"\n");
		sb.append(weather.getWind()+"\n");
		sb.append(weather.getClouds()+"\n");
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
		List<WeatherForecast> list = forecast.getWeatherForecasts();
		
		return ""+forecast.getWeatherForecasts();
	}
	
}
