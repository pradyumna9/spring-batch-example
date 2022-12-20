package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.mapper.LiquidToLiquidTransformedMapper;
import com.spring.batch.springbatchexample.model.CountryMap;
import com.spring.batch.springbatchexample.model.Liquid;
import com.spring.batch.springbatchexample.model.TransformedLiquid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LiquidItemProcessor implements ItemProcessor<Liquid, TransformedLiquid> {

    private Logger LOGGER = LoggerFactory.getLogger(LiquidItemProcessor.class);

    @Autowired
    private CountryMap countryMap;
    @Autowired
    private LiquidToLiquidTransformedMapper liquidToLiquidTransformedMapper;

    @Override
    public TransformedLiquid process(Liquid liquid) throws Exception {
        LOGGER.info("Liquid Process........");
        String countryCode = countryMap.getCountryMap().get(liquid.getCountryCode());
        liquid.setCountryCode(countryCode);
        TransformedLiquid transformedLiquid = liquidToLiquidTransformedMapper.liquidToTransformedLiquid(liquid);
        LOGGER.info("Liquid Processed........:{}",countryCode);
        return transformedLiquid;
    }
}
