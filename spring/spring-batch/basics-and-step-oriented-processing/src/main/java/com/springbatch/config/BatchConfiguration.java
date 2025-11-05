package com.springbatch.config;

import com.springbatch.decider.CustomJobExecutionDecider;
import com.springbatch.listener.CustomStepExecutionListener;
import lombok.NonNull;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@SuppressWarnings("unused")
public class BatchConfiguration {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;

  @Autowired private CustomStepExecutionListener customStepExecutionListener;

  public BatchConfiguration(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
  }

  @Bean
  public JobExecutionDecider customJobExecutionDecider() {
    return new CustomJobExecutionDecider();
  }

  @Bean
  public Step firstStep() {
    return new StepBuilder("firstStep", jobRepository)
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(
                  @NonNull final StepContribution stepContribution,
                  @NonNull final ChunkContext chunkContext) {
                System.out.println("Step 1 executed");
                return RepeatStatus.FINISHED;
              }
            },
            transactionManager)
        .build();
  }

  @Bean
  public Step secondStep() {
    //boolean isSuccess = false;
    return new StepBuilder("secondStep", jobRepository)
        .tasklet(
            (stepContribution, chunkContext) -> {
              /*if (isSuccess) {
                throw new Exception("Testing exception");
              }*/
              System.out.println("Step 2 executed");
              return RepeatStatus.FINISHED;
            },
            transactionManager)
        .listener(customStepExecutionListener)
        .build();
  }

  @Bean
  public Step thirdStep() {
    return new StepBuilder("thirdStep", jobRepository)
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 3 executed");
              return RepeatStatus.FINISHED;
            },
            transactionManager)
        .build();
  }

  @Bean
  public Step fourthStep() {
    return new StepBuilder("fourthStep", jobRepository)
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 4 executed");
              return RepeatStatus.FINISHED;
            },
            transactionManager)
        .build();
  }

  @Bean
  public Step fifthStep() {
    return new StepBuilder("fifthStep", jobRepository)
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 5 executed");
              return RepeatStatus.FINISHED;
            },
            transactionManager)
        .build();
  }

  @Bean
  public Step sixthStep() {
    return new StepBuilder("sixthStep", jobRepository)
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 6 executed");
              return RepeatStatus.FINISHED;
            },
            transactionManager)
        .build();
  }



  @Bean
  public Job firstJob(Step firstStep , Step secondStep , Step thirdStep , Step fourthStep , Step fifthStep) {
    return new JobBuilder("firstJob",jobRepository)
        .start(firstStep)
          .on("COMPLETED")
          .to(customJobExecutionDecider())
          .from(customJobExecutionDecider())
          .on("TEST_DECIDER_STATUS") // the status is not persisted in DB in case of JobExecutionDecider
          .to(secondStep)
          .from(secondStep()) //conditional flow
            .on("TEST_LISTENER_STATUS")
            .to(thirdStep)
          .from(secondStep) //conditional flow
            .on("FAILED")
            .to(fourthStep)
          .from(secondStep)
            .on("*") // catch all
            .to(fifthStep)
          .end()
        .build();
    // on to from, on to from ,...... , on to end.
  }
}