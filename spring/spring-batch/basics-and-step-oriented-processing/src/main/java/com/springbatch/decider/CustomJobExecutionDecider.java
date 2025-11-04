package com.springbatch.decider;

import lombok.NonNull;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class CustomJobExecutionDecider implements JobExecutionDecider {

  @Override
  @NonNull public FlowExecutionStatus decide(
      @NonNull JobExecution jobExecution, StepExecution stepExecution) {
    return new FlowExecutionStatus("TEST_DECIDER_STATUS");
  }
}
