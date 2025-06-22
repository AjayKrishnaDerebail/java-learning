package com.springbatch.config;


import com.springbatch.decider.CustomJobExecutionDecider;
import com.springbatch.listener.CustomStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
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
public class BatchConfiguration {
  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public StepExecutionListener customStepExecutionListener(){
      return new CustomStepExecutionListener();
  }

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
  public Job firstJob(){

    return this.jobBuilderFactory
        .get("firstJob")
        .start(firstStep())
        .build();
  }
}
