package com.klm.cases.df.actuator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trace {
	private int timeTaken;
	private Request request;
	private Response response;

}
