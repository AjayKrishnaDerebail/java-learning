package com.java_9_to_25.gatherers;

import java.util.stream.Gatherers;

public class StreamGathererWindowSliding {

  public static void streamGathererWindowSlidingDemo(){
    var movies = MoviesDatabase.getMovies();

    movies.stream()
        .limit(5)
        .gather(Gatherers.windowSliding(2))
        .forEach(movie -> {
          IO.println("Sliding window:");
          movie.forEach(movie1 ->
              IO.println(" - " + movie1.name() + "( " + movie1.releaseDate()  + ")" ));
        });
  }

}