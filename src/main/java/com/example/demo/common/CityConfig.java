package com.example.demo.common;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "city-info")
public class CityConfig {

	private Map<String, String> cityMap;
}
