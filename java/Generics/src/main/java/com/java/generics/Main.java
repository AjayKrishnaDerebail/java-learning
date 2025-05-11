package com.java.generics;

import com.java.generics.challenge.Layer;
import com.java.generics.challenge.Park;
import com.java.generics.challenge.River;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello and welcome!");
    var nationalUSParks = new Park[]{
        new Park("Yellowstone", "44.482 , -110.833"),
        new Park("Yosemite", "37.860 , -119.550"),
        new Park("Grand Canyon", "35.190 , -113.930"),
        new Park("Zion", "37.300 , -113.050")
    };

    Layer<Park> parkLayer = new Layer<>(nationalUSParks);
    parkLayer.renderLayer();

    System.out.println();

    var majorUSRivers = new River[]{
        new River("Missouri", "39.000 , -94.500", "29.156 , -94.500"),
        new River("Mississippi", "32.000 , -90.000", "30.000 , -90.000"),
        new River("Colorado", "39.000 , -105.000", "38.000 , -105.000")
    };

    Layer<River> riverLayer = new Layer<>(majorUSRivers);
    riverLayer.renderLayer();
  }
}