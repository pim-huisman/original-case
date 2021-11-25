package com.klm.cases.df.travel.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmbeddedLocation {

	List<Location> locations;
}
