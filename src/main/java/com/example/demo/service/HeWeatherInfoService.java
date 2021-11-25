package com.example.demo.service;

import java.io.IOException;

import com.example.demo.heweather.HeWeatherResponse;

/**
 * 天气预报和实况
 * add by djc
 */
public interface HeWeatherInfoService {

    //天气预报和实况 实时
    public HeWeatherResponse getWeatherInfo(String location, String lang) throws IOException;

}
