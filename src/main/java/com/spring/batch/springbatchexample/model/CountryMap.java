package com.spring.batch.springbatchexample.model;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.util.Map;
@Data
@Configuration
@ConfigurationProperties(prefix = "country")
public class CountryMap {
    private Map<String,String> countryMap;
}
