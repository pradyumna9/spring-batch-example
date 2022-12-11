package com.spring.batch.springbatchexample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TransformedLiquid {
    @Id
    private int liquidId;
    private String liquidRef;
    private String countryCode;
    private String type;
}
