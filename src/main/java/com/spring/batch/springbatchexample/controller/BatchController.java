package com.spring.batch.springbatchexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BatchController {

    private Logger LOGGER = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/load")
    public BatchStatus getBatchJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        LOGGER.info("Spring Batch Controller Started");
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        JobParameters jobParameters = new JobParameters(jobParameterMap);
        JobExecution jobExecution = jobLauncher.run(job,jobParameters);
        LOGGER.info("Job Execution status:{}",jobExecution.getStatus());
        while(jobExecution.isRunning()){
            LOGGER.info("...........");
        }
        LOGGER.info("Batch Execution Completed");
        return jobExecution.getStatus();
    }
}
