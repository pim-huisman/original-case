package com.klm.cases.df.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Metrics {
	
	private long maxTime;
	private long minTime;
	private long avgTime;
	private long totalCount;
	private long statusOKCount;
	private long status4XXCount;
	private long status5XXCount;
	
}
