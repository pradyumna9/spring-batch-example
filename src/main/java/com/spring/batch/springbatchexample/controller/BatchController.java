package com.spring.batch.springbatchexample.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BatchController {

    private JobLauncher jobLauncher;

    private Job job;

    public BatchController(JobLauncher jobLauncher,Job job){
        jobLauncher = jobLauncher;
        job = job;
    }

    @GetMapping("/load")
    public BatchStatus getBatchJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Spring Batch Controller Started");
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        JobParameters jobParameters = new JobParameters(jobParameterMap);
        JobExecution jobExecution = jobLauncher.run(job,jobParameters);
        System.out.println("Job Execution status:"+jobExecution.getStatus());
        while(jobExecution.isRunning()){
            System.out.println("........");
        }
        System.out.println("Batch Execution Completed");
        return jobExecution.getStatus();
    }
}
