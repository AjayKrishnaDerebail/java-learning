package com.java_9_to_25.gatherers;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public record Movie(
    String name,
    LocalDate releaseDate,
    Genre genre,
    int durationMinutes,
    double rating,
    Set<String> tags
) {

  public Movie {
    Objects.requireNonNull(name, "name");
    Objects.requireNonNull(releaseDate, "releaseDate");
    Objects.requireNonNull(genre, "genre");
    Objects.requireNonNull(tags, "tags");

    if (name.isBlank()) {
      throw new IllegalArgumentException("name must be non-blank");
    }
    if (durationMinutes <= 0) {
      throw new IllegalArgumentException("durationMinutes must be > 0");
    }
    if (rating < 0.0 || rating > 10.0) {
      throw new IllegalArgumentException("rating must be between 0.0 and 10.0");
    }
  }

  public enum Genre {
    ACTION,
    COMEDY,
    DRAMA,
    HORROR,
    ROMANCE,
    SCI_FI,
    THRILLER,
    ANIMATION,
    DOCUMENTARY
  }
}
