package com.java.generics.simple.model;

public interface Container<T> {
  void add(T t);

  T get();
}
