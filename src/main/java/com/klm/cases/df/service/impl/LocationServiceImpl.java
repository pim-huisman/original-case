package com.klm.cases.df.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.klm.cases.df.service.LocationService;
import com.klm.cases.df.util.MetricsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.klm.cases.df.actuator.Root;
import com.klm.cases.df.config.AppConfig;
import com.klm.cases.df.model.Fare;
import com.klm.cases.df.model.Location;
import com.klm.cases.df.model.Locations;
import com.klm.cases.df.model.Metrics;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private RestOperations restOperations;

	@Autowired
	AppConfig appConfig;

	public Locations listLocations(String searchTerm, Integer page, Integer size) {
		String url = appConfig.getServiceApiUrl() + "/airports";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
		if (searchTerm != null && !searchTerm.isEmpty()) {
			uriBuilder.queryParam("term", searchTerm);
		}
		if (page != null) {
			uriBuilder.queryParam("page", page);
		}
		if (size != null) {
			uriBuilder.queryParam("size", size);
		}

		return restOperations.getForEntity(uriBuilder.build().toString(), Locations.class).getBody();
	}
	
	public Location getLocationByCode(String code) {
		String url = appConfig.getServiceApiUrl() + "/airports/" + code;
		return restOperations.getForEntity(url, Location.class).getBody();
	}


	
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<Location> findLocationByCodeAsync(String code) throws InterruptedException {
		Location location = getLocationByCode(code);
		return CompletableFuture.completedFuture(location);
	}
	

}
