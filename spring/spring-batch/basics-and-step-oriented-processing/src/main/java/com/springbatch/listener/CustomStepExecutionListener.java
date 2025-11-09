package com.springbatch.listener;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomStepExecutionListener implements StepExecutionListener {

  @Override
  public void beforeStep(@NonNull StepExecution stepExecution) {
    log.info(
        "Step started : {} at : {}", stepExecution.getStepName(), stepExecution.getStartTime());
    log.info("Step parameters : {}", stepExecution.getJobParameters());
    log.info("{} executed on thread : {}", stepExecution.getStepName(),
        Thread.currentThread().getName());
  }

  @Override
  public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
    log.info("Step finished : {} at : {}", stepExecution.getStepName(), stepExecution.getEndTime());
    return new ExitStatus("TEST_LISTENER_STATUS");
  }
}