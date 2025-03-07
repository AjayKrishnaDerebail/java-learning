package com.spring.mvc.configuration;

import com.spring.mvc.aspect.PaymentAspect;
import com.spring.mvc.service.PaymentService;
import com.spring.mvc.service.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

  // Define the PaymentService bean
  @Bean
  public PaymentService paymentService() {
    return new PaymentServiceImpl();
  }

  @Bean
  public PaymentAspect loggingAspect() {
    return new PaymentAspect();
  }
}
