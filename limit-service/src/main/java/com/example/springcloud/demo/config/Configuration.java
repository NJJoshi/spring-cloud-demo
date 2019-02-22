package com.example.springcloud.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("limit-service")
@Data
@NoArgsConstructor
public class Configuration {
	private int minimum;
	private int maximum;
}
