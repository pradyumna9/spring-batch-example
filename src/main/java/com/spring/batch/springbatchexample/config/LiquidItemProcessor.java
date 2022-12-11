package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.mapper.LiquidToLiquidTransformedMapper;
import com.spring.batch.springbatchexample.model.Liquid;
import com.spring.batch.springbatchexample.model.TransformedLiquid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class LiquidItemProcessor implements ItemProcessor<Liquid, TransformedLiquid> {
    Map<String,String> COUNTRY_CODE_MAP;

    @Autowired
    private LiquidToLiquidTransformedMapper liquidToLiquidTransformedMapper;

    private Logger LOGGER = LoggerFactory.getLogger(LiquidItemProcessor.class);


    public LiquidItemProcessor(){
        COUNTRY_CODE_MAP = new HashMap<>();
    COUNTRY_CODE_MAP.put("IN","INDIA");
    COUNTRY_CODE_MAP.put("ENG","ENGLISH");
    COUNTRY_CODE_MAP.put("ZAR","SOUTH AFRICA");
    }
    @Override
    public TransformedLiquid process(Liquid liquid) throws Exception {
        //System.out.println("Liquid Process........");
        LOGGER.info("Liquid Process........");
        String countryCode = COUNTRY_CODE_MAP.get(liquid.getCountryCode());
        liquid.setCountryCode(countryCode);
        TransformedLiquid transformedLiquid = liquidToLiquidTransformedMapper.liquidToTransformedLiquid(liquid);
        //System.out.println("Liquid Processed........:"+countryCode);
        LOGGER.info("Liquid Processed........:{}",countryCode);
        return transformedLiquid;
    }
}
