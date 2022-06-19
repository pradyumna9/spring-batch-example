package com.spring.batch.springbatchexample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TransformedMandate {
    @Id
    private int mandateId;
    private String mandateRef;
    private String countryCode;
    private String type;
}
