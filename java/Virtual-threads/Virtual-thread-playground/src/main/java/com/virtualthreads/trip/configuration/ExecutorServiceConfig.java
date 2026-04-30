package com.virtualthreads.trip.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnThreading;
import org.springframework.boot.thread.Threading;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {

  @Bean
  @ConditionalOnThreading(Threading.VIRTUAL)
  public ExecutorService virtualThreadExecutor() {
    return Executors.newVirtualThreadPerTaskExecutor();
  }

  @Bean
  @ConditionalOnThreading(Threading.PLATFORM)
  public ExecutorService platformThreadExecutor() {
    return Executors.newCachedThreadPool();
  }

}