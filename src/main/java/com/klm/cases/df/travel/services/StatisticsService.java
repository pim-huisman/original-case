package com.klm.cases.df.travel.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatisticsService {

	@Value("${travel.metricUrlPath}")
	private String METRIC_URL_PAth;

    private final RestTemplate restTemplate;
    
    @Autowired
    public StatisticsService(@Qualifier("restTemplate") final RestTemplate restTemplate) {
    	this.restTemplate = restTemplate;
    }
    
	public ResponseEntity<Map> getMetrics() {
		return restTemplate.getForEntity(METRIC_URL_PAth, Map.class);
	}
}
