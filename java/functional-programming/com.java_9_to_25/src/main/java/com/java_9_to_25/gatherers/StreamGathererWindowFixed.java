package com.java_9_to_25.gatherers;

import java.util.stream.Gatherers;

public class StreamGathererWindowFixed {

  public static void streamGathererWindowFixedDemo(){

    var movies = MoviesDatabase.getMovies();

    movies.stream()
        .gather(Gatherers.windowFixed(3))
        .forEach(movie -> {
          IO.println("Window:");
          movie.forEach(movie1 ->
              IO.println(" - " + movie1.name() + "( " + movie1.releaseDate()  + ")" ));
        });
  }

}