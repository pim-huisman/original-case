package com.klm.cases.df.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
	private int size;
	private int totalElements;
	private int totalPages;
	private int number;
}
