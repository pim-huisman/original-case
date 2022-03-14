package com.klm.cases.df.service.impl;

import com.klm.cases.df.actuator.Root;
import com.klm.cases.df.config.AppConfig;
import com.klm.cases.df.model.Fare;
import com.klm.cases.df.model.Location;
import com.klm.cases.df.model.Locations;
import com.klm.cases.df.model.Metrics;
import com.klm.cases.df.service.LocationService;
import com.klm.cases.df.service.MetricsService;
import com.klm.cases.df.util.MetricsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class MetricsServiceImpl implements MetricsService {

	@Autowired
	private RestOperations restOperations;

	@Autowired
	AppConfig appConfig;

	public Metrics getHealthMetrics() {
		String url = appConfig.getActuatorUrl() + "/actuator/httptrace";
		Root traceData = restOperations.getForEntity(url , Root.class).getBody();
		MetricsUtil utility = new MetricsUtil();
		Metrics metrics  = utility.processAndReturnMetrics(traceData);
		return metrics ;
	}
}
