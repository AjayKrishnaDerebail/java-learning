package com.java_9_to_25.gatherers;

import java.util.stream.Gatherers;

public class StreamGatherersFold {

  public static void streamGatherersFoldAndScanDemo(){
    var movies = MoviesDatabase.getMovies();

    movies.stream()
        .gather(Gatherers.fold(()-> "",
            (acc,movie) -> acc.isBlank() ? movie.name() : acc + ", " + movie.name()))
        .forEach(IO::println);

    movies.stream()
        .gather(Gatherers.scan(()-> "",
            (acc,movie) -> acc.isBlank() ? movie.name() : acc + ", " + movie.name()))
        .forEach(IO::println);
  }

}