package com.springbatch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class JobLaunchController {

  private static final Logger logger = LoggerFactory.getLogger(JobLaunchController.class);

  private final JobLauncher jobLauncher;

  @Autowired
  JobLaunchController(JobLauncher jobLauncher) {
    this.jobLauncher = jobLauncher;
  }

  @Autowired
  @Qualifier("firstJob")
  private Job job;

  @GetMapping("/launchJob/{id}")
  public ResponseEntity<String> launchJob(@PathVariable("id") String id) {
    logger.info("Received request to launch job with id: {}", id);
    try {
      JobParameters jobParameters = new JobParametersBuilder()
          .addString("id", id)
          .toJobParameters();

      logger.info("Launching job with parameters: {}", jobParameters);
      JobExecution jobExecution = jobLauncher.run(job, jobParameters);
      String response = String.format("Job %s started with status: %s",
          jobExecution.getJobInstance().getJobName(),
          jobExecution.getStatus());
      logger.info(response);
      return ResponseEntity.ok(response);
    } catch (JobInstanceAlreadyCompleteException e) {
      logger.error("Job already completed: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Job already completed: " + e.getMessage());
    } catch (JobExecutionAlreadyRunningException e) {
      logger.error("Job is already running: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Job is already running: " + e.getMessage());
    } catch (JobParametersInvalidException e) {
      logger.error("Invalid job parameters: {}", e.getMessage());
      return ResponseEntity.badRequest().body("Invalid job parameters: " + e.getMessage());
    } catch (JobRestartException e) {
      logger.error("Job restart error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Job restart error: " + e.getMessage());
    } catch (Exception e) {
      logger.error("Unexpected error launching job: {}", e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error launching job: " + e.getMessage());
    }
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("Test endpoint is working!");
  }
}
