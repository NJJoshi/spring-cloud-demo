package com.example.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcloud.demo.config.Configuration;
import com.example.springcloud.demo.model.LimitConfiguration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration limitConfiguration;
	
	@GetMapping("/get/limit")
	public LimitConfiguration getConfiguration() {
		return new LimitConfiguration(limitConfiguration.getMinimum(), limitConfiguration.getMaximum());
	}
	
}
