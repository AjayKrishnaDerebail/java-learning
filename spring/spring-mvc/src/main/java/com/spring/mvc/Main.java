package com.spring.mvc;

import com.spring.mvc.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        com.spring.mvc.configuration.AopConfig.class);

    PaymentService paymentService = applicationContext.getBean(PaymentService.class);
    paymentService.makePayment();
  }
}
