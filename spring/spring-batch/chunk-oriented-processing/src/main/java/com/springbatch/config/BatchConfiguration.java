package com.springbatch.config;

import com.springbatch.reader.ProductNameItemReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  private JobBuilderFactory jobBuilderFactory;

  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Autowired
  public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
  }

  @Bean
  public ItemReader<String> itemReader() {
    List<String> productList = new ArrayList<>();
    productList.add("Product 1");
    productList.add("Product 2");
    productList.add("Product 3");
    productList.add("Product 4");
    productList.add("Product 5");
    productList.add("Product 6");
    productList.add("Product 7");
    productList.add("Product 8");

    return new ProductNameItemReader(productList);
  }

  @Bean
  public Step firstStep() {
    return this.stepBuilderFactory
        .get("chunkBasedFirstStep")
        .<String, String>chunk(2)
        .reader(itemReader())
        .writer(
            new ItemWriter<>() {
              @Override
              public void write(List<? extends String> items) {
                System.out.println("Chunk processing started");
                items.forEach(System.out::println);
                System.out.println("Chunk processing ended");
              }
            })
        .build();
  }

  @Bean
  public Job firstJob() {
    return this.jobBuilderFactory.get("firstJob").start(firstStep()).build();
  }
}
