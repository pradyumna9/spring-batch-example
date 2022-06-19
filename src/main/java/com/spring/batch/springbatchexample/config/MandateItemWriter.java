package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.model.Mandate;
import com.spring.batch.springbatchexample.model.TransformedMandate;
import com.spring.batch.springbatchexample.repo.MandateRepo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MandateItemWriter implements ItemWriter<TransformedMandate> {
    @Autowired
    private MandateRepo mandateRepo;

    @Override
    public void write(List<? extends TransformedMandate> list) throws Exception {
        System.out.println("Mandate Writer Save Successfully:"+list);
        mandateRepo.saveAll(list);
    }
}
