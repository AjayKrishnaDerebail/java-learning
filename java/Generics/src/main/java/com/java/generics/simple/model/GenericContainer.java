package com.java.generics.simple.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericContainer<T> implements Container<T>{

  private T item;

  @Override
  public void add(T item) {
    this.item = item;
  }

  @Override
  public T get() {
    return item;
  }
}
