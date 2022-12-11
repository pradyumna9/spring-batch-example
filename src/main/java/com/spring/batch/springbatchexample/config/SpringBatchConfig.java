package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.model.Liquid;
import com.spring.batch.springbatchexample.model.TransformedLiquid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private Logger LOGGER = LoggerFactory.getLogger(SpringBatchConfig.class);

    @Bean
    public Job createJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                         ItemReader<Liquid> itemReader
                       , ItemWriter<TransformedLiquid> itemWriter
            , ItemProcessor<Liquid, TransformedLiquid> itemProcessor){
        Step step = stepBuilderFactory.get("MY-FIRST-STEP").<Liquid, TransformedLiquid>chunk(100)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
        return jobBuilderFactory.get("MY-FIRST-JOB").start(step).build();
    }

    @Bean
    public FlatFileItemReader<Liquid> getItemReader(@Value("${liquid_reader}") Resource resource){
      //  System.out.println("Item Reader Started");
        LOGGER.info("Item Reader Started");
        FlatFileItemReader<Liquid> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setStrict(true);
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(getLineMapper());
        return flatFileItemReader;
    }

    private DefaultLineMapper<Liquid> getLineMapper(){
        DefaultLineMapper<Liquid> lineMapper = new DefaultLineMapper<>();
        BeanWrapperFieldSetMapper<Liquid> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Liquid.class);
        beanWrapperFieldSetMapper.setStrict(false);

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames("liquidId","liquidRef","countryCode","type");
        delimitedLineTokenizer.setStrict(true);

        lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        return  lineMapper;
    }

}
