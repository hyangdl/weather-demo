package com.example.demo.vo;

import lombok.Data;

@Data
public class CityWeatherInfo {

	private String cityId;
	private String cityName;
	
	private String weather;
	private double temperature;
	private String windSpeed;
	private String updatedTime;
	
	private String errorMsg;
}
