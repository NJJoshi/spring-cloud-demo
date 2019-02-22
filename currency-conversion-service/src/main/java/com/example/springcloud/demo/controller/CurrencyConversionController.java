package com.example.springcloud.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.springcloud.demo.model.CurrencyConversion;
import com.example.springcloud.demo.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable("from") String from, 
											  @PathVariable("to") String to, 
											  @PathVariable("quantity") BigDecimal quantity) {
		String port = environment.getProperty("local.server.port");
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
		Map<String, String> uriMap = new HashMap<>();
		uriMap.put("from", from);
		uriMap.put("to", to);
		ResponseEntity<CurrencyConversion> result = restTemplate.getForEntity(url, CurrencyConversion.class, uriMap);
		CurrencyConversion obj = result.getBody();
		return new CurrencyConversion(obj.getId(), obj.getFrom(), obj.getTo(), obj.getConversionMultiple(), quantity, quantity.multiply(obj.getConversionMultiple()), port);
	}
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable("from") String from, 
											  @PathVariable("to") String to, 
											  @PathVariable("quantity") BigDecimal quantity) {
		String port = environment.getProperty("local.server.port");
		CurrencyConversion obj = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(obj.getId(), obj.getFrom(), obj.getTo(), obj.getConversionMultiple(), quantity, quantity.multiply(obj.getConversionMultiple()), port);
	}	
}
