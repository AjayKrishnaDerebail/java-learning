package com.java.generics.simple.model;

public class Main {

  public static void main(String[] args) {
    Box<Integer> box = new Box<>();
    box.setValue(10);
    System.out.println(box.getValue());

    box.setValue(Integer.valueOf("Hello"));
    System.out.println(box.getValue());
  }

}
