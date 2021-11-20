package com.klm.cases.df.service.impl;

import com.klm.cases.df.config.AppConfig;
import com.klm.cases.df.model.Fare;
import com.klm.cases.df.model.Location;
import com.klm.cases.df.service.FareService;
import com.klm.cases.df.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	LocationService locationService;

	@Autowired
	private RestOperations restOperations;

	@Autowired
	AppConfig appConfig;



	public Fare getFare(String originCode, String destinationCode){
		String url = appConfig.getServiceApiUrl()+"/fares/" + originCode + "/" + destinationCode;
		Fare fare =  restOperations.getForEntity(url , Fare.class).getBody();
		try {
			CompletableFuture<Location> origin = locationService.findLocationByCodeAsync(fare.getOrigin());
			CompletableFuture<Location> destination = locationService.findLocationByCodeAsync(fare.getOrigin());
			CompletableFuture.allOf(origin,destination).join();
			fare.setOriginLocation(origin.get());
			fare.setDest (destination.get());
		} catch (InterruptedException e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ExecutionException e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return fare;
	}
	

	

}
