package com.spring.mvc.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentAspect {

  @Before("execution(* com.spring.mvc.service.PaymentService.makePayment(..))")
  public void printBefore(){
    System.out.println("Before payment starts");
  }

  @After("execution(* com.spring.mvc.service.PaymentService.makePayment(..))")
  public void printAfter(){
    System.out.println("After payment ends");
  }

}
