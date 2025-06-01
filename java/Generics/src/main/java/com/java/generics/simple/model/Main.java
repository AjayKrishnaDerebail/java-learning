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

    Box<Double> doubleBox = new Box<>();
    doubleBox.setValue(10.0);
    log.info(doubleBox.getValue().toString());
    try{
      doubleBox.setValue(Double.valueOf("Hello"));
    } catch (NumberFormatException e) {
      log.error(e.getMessage());
    }

    GenericContainer<String> genericContainer = new GenericContainer<>("Hello");
    genericContainer.add("Hello");
    log.info(genericContainer.get());

    Container<Integer> container = new GenericContainer<>(20);
    container.add(10);
    log.info(container.get().toString());

    Box<Double> doubleBox2 = new Box<>();
    doubleBox2.printBoxName(new Double[]{1.0, 2.0, 3.0});
  }

}
