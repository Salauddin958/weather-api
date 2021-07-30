package com.epam.weatherapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epam.weatherapi.Model.WeatherModel;

@Repository
public interface WeatherRepositry extends MongoRepository<WeatherModel, String>{

}
