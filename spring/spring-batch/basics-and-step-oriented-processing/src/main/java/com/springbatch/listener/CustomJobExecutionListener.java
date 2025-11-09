package com.springbatch.listener;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class CustomJobExecutionListener implements JobExecutionListener {
  @Override
  public void beforeJob(@NonNull JobExecution jobExecution) {
    log.info(
        "Job started : {} at : {}",
        jobExecution.getJobInstance().getJobName(),
        jobExecution.getStartTime());
    log.info("Job parameters : {}", jobExecution.getJobParameters());
  }

  @Override
  public void afterJob(@NonNull JobExecution jobExecution) {
    log.info(
        "Job finished : {} at : {}",
        jobExecution.getJobInstance().getJobName(),
        jobExecution.getEndTime());
    log.info("Job status : {}", jobExecution.getStatus());
  }
}