package com.spring.batch.springbatchexample.model;

import lombok.Data;


@Data
public class Mandate {
    private int mandateId;
    private String mandateRef;
    private String countryCode;
    private String type;
}
