package com.example.demo.heweather;

import lombok.Data;

/**
 * 返回实体类
 */
@Data
public class HeWeatherResponse {

    private String status;
    //当前API的最近更新时间
    private String updateTime;
    //该城市的天气预报和实况自适应网页，可嵌入网站或应用
    private String fxLink;
    //
    private WeatherInfo weatherInfo;

}
