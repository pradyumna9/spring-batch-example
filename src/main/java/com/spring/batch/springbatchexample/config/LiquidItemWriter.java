package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.model.TransformedLiquid;
import com.spring.batch.springbatchexample.repo.LiquidRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LiquidItemWriter implements ItemWriter<TransformedLiquid> {
    @Autowired
    private LiquidRepo liquidRepo;
    private Logger LOGGER = LoggerFactory.getLogger(LiquidItemWriter.class);

    @Override
    public void write(List<? extends TransformedLiquid> list) throws Exception {
       // System.out.println("Liquid Writer Save Successfully:"+list);
        LOGGER.info("Liquid Writer Save Successfully:{}",list);
        liquidRepo.saveAll(list);
    }
}
