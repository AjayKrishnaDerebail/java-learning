package com.java.generics.challenge;

import java.util.Arrays;

public abstract class Line implements Mappable {
  private final double[][] locations;

  public Line(String... locations) {
    this.locations = new double[locations.length][];
    int index = 0;
    for (var location : locations) {
      this.locations[index] = Mappable.stringToLatitudeLongitude(location);
      index++;
    }
  }

  private String locations(){
    return Arrays.deepToString(locations);
  }

  @Override
  public void render(){
    System.out.println("Render " + this + "as LINE (" + locations() + ")");
  }
}
