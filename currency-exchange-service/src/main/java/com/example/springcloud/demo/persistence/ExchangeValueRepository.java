package com.example.springcloud.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcloud.demo.model.ExchangeValue;

public interface ExchangeValueRepository extends 
		JpaRepository<ExchangeValue, Long>{
	ExchangeValue findByFromAndTo(String from, String to);
}

