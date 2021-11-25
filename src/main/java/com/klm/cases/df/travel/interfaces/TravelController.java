package com.klm.cases.df.travel.interfaces;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.klm.cases.df.travel.model.Fare;
import com.klm.cases.df.travel.model.Location;
import com.klm.cases.df.travel.services.FareService;
import com.klm.cases.df.travel.services.LocationService;
import com.klm.cases.df.travel.services.StatisticsService;

@Controller
public class TravelController {

	@Autowired
	private FareService fareService;
	
	@Autowired
	private LocationService locationService;

	@Autowired
	private StatisticsService statisticsService;
	
    @GetMapping("/")
    public String openPage() {   	 
    	return "home";
    }
  
    @PostMapping("/findLocation")
    public ResponseEntity<List<String>> findLocation(final HttpServletRequest request,
    		                           @RequestParam("term") final String term,
    		                           @RequestParam("currency") final String currency) {
    	final List<Location> locationsList = locationService.find(term, currency).get_embedded().getLocations();
    	final List<String> locations = locationsList.stream()
    			                                    .map(location -> location.getCode())
    		                                       	.collect(Collectors.toList());
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    
    @PostMapping("/fare")
    public ModelAndView getFare(final HttpServletRequest request) throws InterruptedException, ExecutionException {
    	final String origin = request.getParameter("origin");
    	final String destination = request.getParameter("destination");
    	final String currency = request.getParameter("currency");
    	
    	final ModelAndView mav = new ModelAndView();
        Fare fare = fareService.calculateFare(origin, destination, currency);
        mav.addObject("fare",fare);
        mav.setViewName("result");
        return mav;
     }


    @GetMapping(value = "/statistics")
	public Map getStatistics() {
	    return statisticsService.getMetrics().getBody();
	}
    
}
