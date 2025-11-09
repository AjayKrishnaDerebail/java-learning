package com.springbatch.controller;

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

  private final JobLauncher jobLauncher;

  @Autowired
  JobLaunchController(JobLauncher jobLauncher) {
    this.jobLauncher = jobLauncher;
  }

  @Autowired
  @Qualifier("thirdJob")
  private Job job;

  /**
   * Launch a job with the given id.
   * @param id the id to pass as a job parameter
   * @return a ResponseEntity containing the status of the job execution
   * @throws JobInstanceAlreadyCompleteException if the job instance is already complete
   * @throws JobExecutionAlreadyRunningException if a job execution for the job instance is already running
   * @throws JobParametersInvalidException if the job parameters are invalid
   * @throws JobRestartException if the job instance cannot be restarted
   */
  @GetMapping("/launchJob/{id}")
  public ResponseEntity<String> launchJob(@PathVariable("id") String id)
      throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
          JobParametersInvalidException, JobRestartException {
    JobParameters jobParameters = new JobParametersBuilder().addString("id", id).toJobParameters();
    JobExecution jobExecution = jobLauncher.run(job, jobParameters);
    return new ResponseEntity<>(
        "Job started with status : " + jobExecution.getStatus(), HttpStatus.OK);
  }
}
