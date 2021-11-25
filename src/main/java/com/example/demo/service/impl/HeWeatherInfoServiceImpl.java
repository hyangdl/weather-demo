package com.example.demo.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.heweather.HeWeatherResponse;
import com.example.demo.heweather.WeatherInfo;
import com.example.demo.heweather.Now;
import com.example.demo.service.HeWeatherInfoService;

/**
 * Realtime Weather Service
 */
@Service
public class HeWeatherInfoServiceImpl implements HeWeatherInfoService {
	
	@Value("${he-weather.client-key}")
    private String clientKey;
	
	@Value("${he-weather.now-api}")
    private String nowApi;

    public HeWeatherResponse getWeatherInfo(String location, String lang) throws IOException {
        HeWeatherResponse heWeatherResponse = new HeWeatherResponse();

        HashMap<String, String> params = new HashMap<>();
        params.put("key", clientKey);
        String t = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("t", t);
        if (!StringUtils.isEmpty(lang)) {
            params.put("lang", lang);
        } else {
        	params.put("lang", "en");
        }
        if (!StringUtils.isEmpty(location)) {
        	params.put("location", location);
        } else {
        	heWeatherResponse.setStatus("400");
        	return heWeatherResponse;
        }
        
        List<String> paramList = params.keySet().stream().map(mapKey -> (mapKey + "=" + params.get(mapKey))).collect(Collectors.toList());
        String url = nowApi + String.join("&", paramList);
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String json = EntityUtils.toString(httpResponse.getEntity());
            JSONObject response = (JSONObject) JSON.parse(json);
            if (response.get("code").equals("200")) {
            	heWeatherResponse.setStatus("200");
                WeatherInfo weatherInfo = new WeatherInfo();
                weatherInfo.setUpdateTime(response.getString("updateTime"));
                weatherInfo.setFxLink(response.getString("fxLink"));
                
                if (response.containsKey("now")) {
                    JSONObject jsonNow = response.getJSONObject("now");
                    Now now = new Now();
                    now.setCloud(jsonNow.getString("cloud"));
                    now.setDew(jsonNow.getString("dew"));
                    now.setFeelsLike(jsonNow.getString("feelsLike"));
                    now.setHumidity(jsonNow.getString("humidity"));
                    now.setIcon(jsonNow.getString("icon"));
                    now.setObsTime(jsonNow.getString("obsTime"));
                    now.setPrecip(jsonNow.getString("precip"));
                    now.setPressure(jsonNow.getString("pressure"));
                    now.setTemp(jsonNow.getString("temp"));
                    now.setText(jsonNow.getString("text"));
                    now.setVis(jsonNow.getString("vis"));
                    now.setWind360(jsonNow.getString("wind360"));
                    now.setWindDir(jsonNow.getString("windDir"));
                    now.setWindScale(jsonNow.getString("windScale"));
                    now.setWindSpeed(jsonNow.getString("windSpeed"));
                    weatherInfo.setNow(now);
                    heWeatherResponse.setWeatherInfo(weatherInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            heWeatherResponse.setStatus("400");
        } finally {
        	if (httpClient != null) {
        		httpClient.close();
        	}
        }
        return heWeatherResponse;
    }
}
