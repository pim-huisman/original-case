package com.klm.cases.df.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.cases.df.service.LocationService;
import com.klm.cases.df.model.Location;
import com.klm.cases.df.model.Locations;

@RestController
@RequestMapping("/airports")
public class AirportsController {

	@Autowired
	LocationService travelService;
	
	@GetMapping
	public Locations listLocations(@RequestParam(name = "term", required=false) String searchTerm, @RequestParam(name = "page", required=false) Integer page, @RequestParam(name = "size", required=false) Integer pageSize){
		return travelService.listLocations(searchTerm, page, pageSize);
	}
	
	@GetMapping("/{code}")
	public Location getByCode(@PathVariable String code){
		return travelService.getLocationByCode(code);
	}
	
}
