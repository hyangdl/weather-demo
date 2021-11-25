package com.example.demo.heweather;

import lombok.Data;

@Data
public class Now {

    private String obsTime; //实况观测时间; //2013-12-30T01:45+08:00
    private String temp; //实况温度，默认单位：摄氏度; //21
    private String feelsLike; //实况体感温度，默认单位：摄氏度; //23
    private String icon; //当前天气状况和图标的代码，图标可通过天气状况和图标下载; //100
    private String text; //实况天气状况的文字描述，包括阴晴雨雪等天气状态的描述; //晴
    private String wind360; //实况风向360角度; //305
    private String windDir; //实况风向; //西北
    private String windScale; //实况风力等级; //3
    private String windSpeed; //实况风速，公里/小时; //15
    private String humidity; //实况相对湿度，百分比数值; //40
    private String precip; //实况降水量，默认单位：毫米; //1.2
    private String pressure; //实况大气压强，默认单位：百帕; //1020
    private String vis; //实况能见度，默认单位：公里; //10
    private String cloud; //实况云量，百分比数值; //23
    private String dew; //实况露点温度; //12

}
