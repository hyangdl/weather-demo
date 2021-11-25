package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CityConfig;
import com.example.demo.heweather.HeWeatherResponse;
import com.example.demo.heweather.Now;
import com.example.demo.service.HeWeatherInfoService;
import com.example.demo.vo.CityWeatherInfo;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {
	
	@Autowired
	private CityConfig cityConfig;
	
	@Autowired
	private HeWeatherInfoService heWeatherInfoService;
	
	/**
	 * query the real-time weather information
	 * @param cityId
	 * @param lang
	 * @return
	 */
	@GetMapping("/get")
	public CityWeatherInfo getCityWeather(@NotNull String cityId, String lang) {
		CityWeatherInfo weatherInfo = new CityWeatherInfo();
		weatherInfo.setCityId(cityId);
		
		String cityName = cityConfig.getCityMap().get(cityId);
		if (cityName == null) {
			weatherInfo.setErrorMsg("This city is out of scope.");
			return weatherInfo;
		} else {
			weatherInfo.setCityName(cityName);
		}
		
		try {
			HeWeatherResponse res = heWeatherInfoService.getWeatherInfo(cityId, lang);
			Now weatherNow = res.getWeatherInfo().getNow();
			weatherInfo.setWeather(weatherNow.getText());
			weatherInfo.setTemperature(Double.parseDouble(weatherNow.getTemp()));
			weatherInfo.setWindSpeed(weatherNow.getWindSpeed());
			weatherInfo.setUpdatedTime(res.getWeatherInfo().getNow().getObsTime());
		} catch (IOException e) {
			e.printStackTrace();
			weatherInfo.setErrorMsg(e.getMessage());
			return weatherInfo;
		}
		
		return weatherInfo;
	}

	/**
	 * get all the city information
	 * @return
	 */
	@GetMapping("/cities")
	public Map<String, String> getCityMap() {
		return cityConfig.getCityMap();
	}
}
