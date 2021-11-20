package com.klm.cases.df.controller;

import com.klm.cases.df.service.LocationService;
import com.klm.cases.df.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klm.cases.df.model.Metrics;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {
	
	@Autowired
	private MetricsService metricsService;
	
	@GetMapping("getHealthMetrics")
	public Metrics getMetricsWithFilter() {
		return metricsService.getHealthMetrics();
	}
}
