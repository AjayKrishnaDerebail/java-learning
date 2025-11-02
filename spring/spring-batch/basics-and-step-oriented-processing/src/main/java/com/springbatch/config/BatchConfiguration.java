package com.springbatch.config;


import com.springbatch.decider.CustomJobExecutionDecider;
import com.springbatch.listener.CustomStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@SuppressWarnings("unused")
public class BatchConfiguration {
  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private CustomStepExecutionListener customStepExecutionListener;

  @Bean
  public JobExecutionDecider customJobExecutionDecider(){
    return new CustomJobExecutionDecider();
  }
  @Bean
  public Step firstStep() {
    return this.stepBuilderFactory
        .get("firstStep")
        .tasklet(
            new Tasklet() {
              @Override
              public RepeatStatus execute(
                  StepContribution stepContribution, ChunkContext chunkContext) {
                System.out.println("Step 1 executed");
                return RepeatStatus.FINISHED;
              }
            })
        .build();
  }

  @Bean
  public Step secondStep() {
    boolean isSuccess = false;
    return this.stepBuilderFactory
        .get("secondStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              if (isSuccess) {
                throw new Exception("Testing exception");
              }
              System.out.println("Step 2 executed");
              return RepeatStatus.FINISHED;
            })
        .listener(customStepExecutionListener) // CustomStepExecutionListener
        .build();
  }

  @Bean
  public Step thirdStep() {
    return this.stepBuilderFactory
        .get("thirdStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 3 executed");
              return RepeatStatus.FINISHED;
            })
        .build();
  }

  @Bean
  public Step fourthStep() {
    return this.stepBuilderFactory
        .get("fourthStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 4 executed");
              return RepeatStatus.FINISHED;
            })
        .build();
  }

  @Bean
  public Step fifthStep() {
    return this.stepBuilderFactory
        .get("fifthStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 5 executed");
              return RepeatStatus.FINISHED;
            })
        .build();
  }

  @Bean
  public Step sixthStep() {
    return this.stepBuilderFactory
        .get("fifthStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              System.out.println("Step 6 executed");
              return RepeatStatus.FINISHED;
            })
        .build();
  }

  @Bean
  public Job firstJob(){
    //.preventRestart()    Suppose the job fails,
    // prevent restart will not let you run the job with the same parameters

    return this.jobBuilderFactory
        .get("firstJob")
        .start(firstStep())
        .on("COMPLETED")
        .to(customJobExecutionDecider())
        .from(customJobExecutionDecider())
        .on("TEST_DECIDER_STATUS") // the status is not persisted in DB in case of JobExecutionDecider
        .to(secondStep())
        .from(secondStep()) //conditional flow
          .on("TEST_LISTENER_STATUS")
          .to(thirdStep())
        .from(secondStep()) //conditional flow
          .on("FAILED")
          .to(fourthStep())
        .from(secondStep())
          .on("*") // catch all
          .to(fifthStep())
        .end()
        .build();
        // on to from, on to from ,...... , on to end.
  }
}