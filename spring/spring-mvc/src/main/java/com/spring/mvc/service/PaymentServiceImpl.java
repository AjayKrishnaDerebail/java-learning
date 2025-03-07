package com.spring.mvc.service;

public class PaymentServiceImpl implements PaymentService {

  @Override
  public void makePayment() {
    System.out.println("Amount debited successfully");



    System.out.println("Amount credited successfully");
  }
}
