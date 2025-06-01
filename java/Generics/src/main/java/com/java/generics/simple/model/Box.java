package com.java.generics.simple.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Box <T extends Number>{
  private T value;
}
