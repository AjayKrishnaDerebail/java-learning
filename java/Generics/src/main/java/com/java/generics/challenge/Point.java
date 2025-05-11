package com.java.generics.challenge;

import java.util.Arrays;

public abstract class Point implements Mappable {

  private double[] location;

  public Point(String location) {
    this.location = Mappable.stringToLatitudeLongitude(location);
  }

  private String locationString() {
    return Arrays.toString(location);
  }

  @Override
  public void render(){
    System.out.println("Render " + this + "as POINT (" + locationString() + ")");
  }
}