package com.klm.cases.df.travel.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {


	    private String code;
		private String name;
		private String description;
	    private Coordinates coordinates;
	    private Location parent;
	    private Set<Location> children;

	

}
