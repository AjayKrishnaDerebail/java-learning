package com.java.generics.simple.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    Box<Integer> box = new Box<>();
    box.setValue(10);
    log.info(box.getValue().toString());
    try{
      box.setValue(Integer.valueOf("Hello"));
    } catch (NumberFormatException e) {
      log.error(e.getMessage());
    }

    System.out.println(box.getValue());
  }

}
