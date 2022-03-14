package com.klm.cases.df.controller;

import com.klm.cases.df.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.cases.df.service.LocationService;
import com.klm.cases.df.model.Fare;

@RestController
@RequestMapping("/fares")
public class FaresController {
	
	@Autowired
	FareService fareService;

	@GetMapping
	public Fare getFare(@RequestParam("origin") String origin, @RequestParam("dest") String destination){
		return fareService.getFare(origin, destination);
	}
	
}
