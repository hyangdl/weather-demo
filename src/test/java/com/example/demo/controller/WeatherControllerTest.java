package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.demo.common.CityConfig;
import com.example.demo.heweather.HeWeatherResponse;
import com.example.demo.heweather.Now;
import com.example.demo.heweather.WeatherInfo;
import com.example.demo.service.HeWeatherInfoService;
import com.example.demo.vo.CityWeatherInfo;

@RunWith(PowerMockRunner.class)
public class WeatherControllerTest {
	@Mock
	private CityConfig cityConfig;
	
	@Mock
	private HeWeatherInfoService heWeatherInfoService;
	
	@InjectMocks
	private WeatherController weatherController;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void test_getCityWeather_outCityScope() {
		String cityId = "124";
		String lang = null;
		Assertions.assertNotNull(weatherController.getCityWeather(cityId, lang).getErrorMsg());
	}
	
	@Test
    public void test_getCityWeather_failed() throws IOException {
		String cityId = "124";
		String lang = "zh";

		Map<String, String> cityMap = new HashMap<>();
		cityMap.put(cityId, "CityName");
		
		Mockito.doThrow(new IOException()).when(heWeatherInfoService).getWeatherInfo(cityId, lang);
		Assertions.assertNotNull(weatherController.getCityWeather(cityId, lang).getErrorMsg());
	}
	
	@Test
    public void test_getCityWeather() throws IOException {
		String cityId = "F2FTd";
		String lang = "en";
		String cityName = "CityName";

		Map<String, String> cityMap = new HashMap<>();
		cityMap.put(cityId, cityName);
		
		HeWeatherResponse res = new HeWeatherResponse();
		WeatherInfo weatherInfo = new WeatherInfo();
		Now weatherNow = new Now();
		weatherNow.setText("weather text");
		weatherNow.setWindSpeed("3");
		weatherNow.setTemp("22");
		weatherInfo.setNow(weatherNow);
		res.setWeatherInfo(weatherInfo);
		
		Mockito.doReturn(cityMap).when(cityConfig).getCityMap();
		Mockito.doReturn(res).when(heWeatherInfoService).getWeatherInfo(cityId, lang);
		
		CityWeatherInfo weather = weatherController.getCityWeather(cityId, lang);
		Assertions.assertEquals(weatherNow.getText(), weather.getWeather());
		Assertions.assertEquals(weatherNow.getWindSpeed(), weather.getWindSpeed());
	}
	
	@Test
    public void test_getCityMap() {
		Map<String, String> cityMap = new HashMap<>();
		cityMap.put("1", "city1");
		cityMap.put("2", "city2");
		cityMap.put("3", "city3");
		
		Mockito.doReturn(cityMap).when(cityConfig).getCityMap();
		Assertions.assertEquals(cityMap, weatherController.getCityMap());
	}
	
}
