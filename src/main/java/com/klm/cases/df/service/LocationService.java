package com.klm.cases.df.service;

import com.klm.cases.df.model.Fare;
import com.klm.cases.df.model.Location;
import com.klm.cases.df.model.Locations;
import com.klm.cases.df.model.Metrics;

import java.util.concurrent.CompletableFuture;

public interface LocationService {
	public Locations listLocations(String searchTerm, Integer page, Integer size);
	public Location getLocationByCode(String code);
	public CompletableFuture<Location> findLocationByCodeAsync(String code) throws InterruptedException;
}
