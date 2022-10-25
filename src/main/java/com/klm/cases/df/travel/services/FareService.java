package com.klm.cases.df.travel.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.klm.cases.df.travel.model.Fare;

import java.util.Base64;
import java.util.concurrent.ExecutionException;



@Service
public class FareService {

	@Value("${travel.fareUrlPath}")
	private String FARE_URL_PAth;
		
	@Value("${travel.username}")
	private String USERNAME;
	
	@Value("${travel.password}")
	private String PASSWORD;
	
	
    private final RestTemplate restTemplate;
    
    @Autowired
    public FareService(@Qualifier("restTemplate") final RestTemplate restTemplate) {
    	this.restTemplate = restTemplate;
    }
    
    public Fare calculateFare(final String origin,
                                        final String destination,
                                        final String currency) throws InterruptedException, ExecutionException {
    
    	final StringBuilder endpointUrl = new StringBuilder();            
    	endpointUrl.append(FARE_URL_PAth)
    	            .append("/")
    	            .append(origin)
    	            .append("/")
    	            .append(destination);
    	
        if(!currency.isBlank()) {
        	endpointUrl.append("?currency=")
        	           .append(currency);
        }
        
        final URI uri = createUri(endpointUrl.toString());
		final HttpEntity<String> entity = new HttpEntity<>(createRequestHeader());
        final ResponseEntity<Fare> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Fare.class);
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
}
