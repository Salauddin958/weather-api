package com.epam.weatherapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.epam.weatherapi.service.WeatherService;

@CrossOrigin
@RestController
public class WeatherController {
	
	@Autowired
	WeatherService service;
	
	
	@GetMapping("/weather/daily/{location}")
	public String getDailyInfo(@PathVariable("location") String location) {
		String weatherData = service.getDailyWeatherInfo(location);
		System.out.println(weatherData);
		return weatherData;
	}
	
	@GetMapping("/weather/historic/{location}")
	public String getHistoricInfo(@PathVariable("location") String location) {
		String weatherData = service.getHistoricWeatherInfo(location);
		return weatherData;
	}
	
	@GetMapping("/weather/future/{location}")
	public String getFutureInfo(@PathVariable("location") String location) {
		String weatherData = service.getFutureWeatherDetails(location);
		return weatherData;
	}

}
	
