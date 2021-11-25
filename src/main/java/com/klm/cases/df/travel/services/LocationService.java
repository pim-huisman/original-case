package com.klm.cases.df.travel.services;

import java.net.URI;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.klm.cases.df.travel.model.LocationList;

@Service
public class LocationService {

	@Value("${travel.locationUrlPath}")
	private String LOCATION_URL_PATH;
	
	@Value("${travel.username}")
	private String USERNAME;
	
	@Value("${travel.password}")
	private  String PASSWORD;
	
	private final ThreadPoolTaskExecutor mvcTaskExecutor;

	private final RestTemplate restTemplate;

	@Autowired
	public LocationService(@Qualifier("restTemplate") final RestTemplate restTemplate,
			@Qualifier("mvcTaskExecutor") final ThreadPoolTaskExecutor mvcTaskExecutor) {
		this.restTemplate = restTemplate;
		this.mvcTaskExecutor = mvcTaskExecutor;
	}
	
	@Async("mvcTaskExecutor")
	public LocationList find(final String term, final String currency) {
    	final StringBuilder endpointUrl = new StringBuilder();            
    	endpointUrl.append(LOCATION_URL_PATH)
    	           .append("?term=")
    	           .append(term);
   	
        if(!currency.isBlank()) {
        	endpointUrl.append("&currency=")
        	           .append(currency);
        }
        
        final URI uri = createUri(endpointUrl.toString());
		final HttpEntity<String> entity = new HttpEntity<>(createRequestHeader());
		final ResponseEntity<LocationList> response = restTemplate.exchange(uri, HttpMethod.GET, entity, LocationList.class);
		return response.getBody();	
	}
	
	private URI createUri(final String endpointUrl) {
		return UriComponentsBuilder.fromUriString(endpointUrl)
            	.queryParam("grant-type", "client_credentials")
            	.queryParam("client-id", "travel-api-client")
                .queryParam("secret", "psw")
                .build()
                .toUri();
	}
	
	private HttpHeaders createRequestHeader() {
		final HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.add(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes()));
		return requestHeaders;
	}
	
	public LocationList get() {
        final URI uri = createUri(LOCATION_URL_PATH);
		final HttpEntity<String> entity = new HttpEntity<>(createRequestHeader());
		final ResponseEntity<LocationList> response = restTemplate.exchange(uri, HttpMethod.GET, entity, LocationList.class);
		return response.getBody();
	}
}
