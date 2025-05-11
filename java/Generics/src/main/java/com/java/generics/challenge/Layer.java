package com.java.generics.challenge;

import java.util.ArrayList;
import java.util.List;

public class Layer<T extends Mappable> {
  private final List<T> layerElements;

  public Layer(T[] layerElements) {
    this.layerElements = new ArrayList<>(List.of(layerElements));
  }

  public void renderLayer() {
    for (T element : layerElements) {
      element.render();
    }
  }
}
