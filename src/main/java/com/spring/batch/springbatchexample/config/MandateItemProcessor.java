package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.mapper.MandateToMandateTransformedMapper;
import com.spring.batch.springbatchexample.model.Mandate;
import com.spring.batch.springbatchexample.model.TransformedMandate;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MandateItemProcessor implements ItemProcessor<Mandate, TransformedMandate> {
    Map<String,String> COUNTRY_CODE_MAP;

    @Autowired
    private MandateToMandateTransformedMapper mandateToMandateTransformedMapper;


    public MandateItemProcessor(){
        COUNTRY_CODE_MAP = new HashMap<>();
    COUNTRY_CODE_MAP.put("IN","INDIA");
    COUNTRY_CODE_MAP.put("ENG","ENGLISH");
    COUNTRY_CODE_MAP.put("ZAR","SOUTH AFRICA");
    }
    @Override
    public TransformedMandate process(Mandate mandate) throws Exception {
        System.out.println("Mandate Process........");
        String countryCode = COUNTRY_CODE_MAP.get(mandate.getCountryCode());
        mandate.setCountryCode(countryCode);
        TransformedMandate transformedMandate = mandateToMandateTransformedMapper.mandateToTransformedMandate(mandate);
        System.out.println("Mandate Processed........:"+countryCode);
        return transformedMandate;
    }
}
