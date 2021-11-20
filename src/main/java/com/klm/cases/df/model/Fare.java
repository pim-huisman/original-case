package com.klm.cases.df.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fare {
	private Double amount;
	private String currency;
	private String destination;
	private String origin;
	private Location originLocation;
	private Location dest;
	
}
