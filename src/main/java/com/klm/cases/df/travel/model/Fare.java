package com.klm.cases.df.travel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fare {

    double amount;
    Currency currency;
    String origin, destination;

}
