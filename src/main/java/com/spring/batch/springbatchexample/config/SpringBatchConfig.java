package com.spring.batch.springbatchexample.config;

import com.spring.batch.springbatchexample.model.Mandate;
import com.spring.batch.springbatchexample.model.TransformedMandate;
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
                         ItemReader<Mandate> itemReader
                       , ItemWriter<TransformedMandate> itemWriter
            , ItemProcessor<Mandate, TransformedMandate> itemProcessor){
        Step step = stepBuilderFactory.get("MY-FIRST-STEP").<Mandate,TransformedMandate>chunk(100)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
        return jobBuilderFactory.get("MY-FIRST-JOB").start(step).build();
    }

    @Bean
    public FlatFileItemReader<Mandate> getItemReader(@Value("${mandate_reader}") Resource resource){
      //  System.out.println("Item Reader Started");
        LOGGER.info("Item Reader Started");
        FlatFileItemReader<Mandate> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setStrict(true);
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(getLineMapper());
        return flatFileItemReader;
    }

    private DefaultLineMapper<Mandate> getLineMapper(){
        DefaultLineMapper<Mandate> lineMapper = new DefaultLineMapper<>();
        BeanWrapperFieldSetMapper<Mandate> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Mandate.class);
        beanWrapperFieldSetMapper.setStrict(false);

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames("mandateId","mandateRef","countryCode","type");
        delimitedLineTokenizer.setStrict(true);

        lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        return  lineMapper;
    }

}
