package com.java_9_to_25.gatherers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MoviesDatabase {

  private static final List<Movie> MOVIES = List.of(
      new Movie(
          "Interstellar",
          LocalDate.of(2014, 11, 7),
          Movie.Genre.SCI_FI,
          169,
          8.6,
          Set.of("space", "time", "nolan")
      ),
      new Movie(
          "The Dark Knight",
          LocalDate.of(2008, 7, 18),
          Movie.Genre.ACTION,
          152,
          9.0,
          Set.of("batman", "joker", "nolan")
      ),
      new Movie(
          "Spirited Away",
          LocalDate.of(2001, 7, 20),
          Movie.Genre.ANIMATION,
          125,
          8.6,
          Set.of("ghibli", "fantasy")
      ),
      new Movie(
          "Parasite",
          LocalDate.of(2019, 5, 30),
          Movie.Genre.DRAMA,
          132,
          8.5,
          Set.of("thriller", "korean")
      ),
      new Movie(
          "Get Out",
          LocalDate.of(2017, 2, 24),
          Movie.Genre.HORROR,
          104,
          7.8,
          Set.of("satire", "thriller")
      ),
      new Movie(
          "Inception",
          LocalDate.of(2010, 7, 16),
          Movie.Genre.SCI_FI,
          148,
          8.8,
          Set.of("dream", "heist", "nolan")
      ),
      new Movie(
          "Dune: Part Two",
          LocalDate.of(2024, 3, 1),
          Movie.Genre.SCI_FI,
          166,
          8.5,
          Set.of("desert", "epic")
      ),
      new Movie(
          "The Matrix",
          LocalDate.of(1999, 3, 31),
          Movie.Genre.SCI_FI,
          136,
          8.7,
          Set.of("simulation", "action")
      ),
      new Movie(
          "Blade Runner 2049",
          LocalDate.of(2017, 10, 6),
          Movie.Genre.SCI_FI,
          164,
          8.0,
          Set.of("neo-noir", "cyberpunk")
      ),
      new Movie(
          "Mad Max: Fury Road",
          LocalDate.of(2015, 5, 15),
          Movie.Genre.ACTION,
          120,
          8.1,
          Set.of("chase", "post-apocalyptic")
      ),
      new Movie(
          "John Wick",
          LocalDate.of(2014, 10, 24),
          Movie.Genre.ACTION,
          101,
          7.4,
          Set.of("assassin", "revenge")
      ),
      new Movie(
          "Gladiator",
          LocalDate.of(2000, 5, 5),
          Movie.Genre.ACTION,
          155,
          8.5,
          Set.of("rome", "epic")
      ),
      new Movie(
          "The Shawshank Redemption",
          LocalDate.of(1994, 9, 23),
          Movie.Genre.DRAMA,
          142,
          9.3,
          Set.of("prison", "hope")
      ),
      new Movie(
          "The Godfather",
          LocalDate.of(1972, 3, 24),
          Movie.Genre.DRAMA,
          175,
          9.2,
          Set.of("mafia", "classic")
      ),
      new Movie(
          "Fight Club",
          LocalDate.of(1999, 10, 15),
          Movie.Genre.DRAMA,
          139,
          8.8,
          Set.of("twist", "cult")
      ),
      new Movie(
          "Forrest Gump",
          LocalDate.of(1994, 7, 6),
          Movie.Genre.DRAMA,
          142,
          8.8,
          Set.of("life", "classic")
      ),
      new Movie(
          "Whiplash",
          LocalDate.of(2014, 10, 10),
          Movie.Genre.DRAMA,
          106,
          8.5,
          Set.of("music", "intense")
      ),
      new Movie(
          "The Silence of the Lambs",
          LocalDate.of(1991, 2, 14),
          Movie.Genre.THRILLER,
          118,
          8.6,
          Set.of("psychological", "crime")
      ),
      new Movie(
          "Se7en",
          LocalDate.of(1995, 9, 22),
          Movie.Genre.THRILLER,
          127,
          8.6,
          Set.of("crime", "dark")
      ),
      new Movie(
          "Gone Girl",
          LocalDate.of(2014, 10, 3),
          Movie.Genre.THRILLER,
          149,
          8.1,
          Set.of("mystery")
      ),
      new Movie(
          "The Conjuring",
          LocalDate.of(2013, 7, 19),
          Movie.Genre.HORROR,
          112,
          7.5,
          Set.of("haunted", "based-on-true-story")
      ),
      new Movie(
          "Hereditary",
          LocalDate.of(2018, 6, 8),
          Movie.Genre.HORROR,
          127,
          7.3,
          Set.of("family", "slow-burn")
      ),
      new Movie(
          "Superbad",
          LocalDate.of(2007, 8, 17),
          Movie.Genre.COMEDY,
          113,
          7.6,
          Set.of("teen", "buddy")
      ),
      new Movie(
          "The Grand Budapest Hotel",
          LocalDate.of(2014, 3, 28),
          Movie.Genre.COMEDY,
          99,
          8.1,
          Set.of("wes-anderson", "stylized")
      ),
      new Movie(
          "Groundhog Day",
          LocalDate.of(1993, 2, 12),
          Movie.Genre.COMEDY,
          101,
          8.0,
          Set.of("time-loop", "classic")
      ),
      new Movie(
          "La La Land",
          LocalDate.of(2016, 12, 9),
          Movie.Genre.ROMANCE,
          128,
          8.0,
          Set.of("music", "dreams")
      ),
      new Movie(
          "Titanic",
          LocalDate.of(1997, 12, 19),
          Movie.Genre.ROMANCE,
          195,
          7.9,
          Set.of("epic", "tragedy")
      ),
      new Movie(
          "Before Sunrise",
          LocalDate.of(1995, 1, 27),
          Movie.Genre.ROMANCE,
          101,
          8.1,
          Set.of("dialogue", "vienna")
      ),
      new Movie(
          "Toy Story",
          LocalDate.of(1995, 11, 22),
          Movie.Genre.ANIMATION,
          81,
          8.3,
          Set.of("pixar", "family")
      ),
      new Movie(
          "Coco",
          LocalDate.of(2017, 11, 22),
          Movie.Genre.ANIMATION,
          105,
          8.4,
          Set.of("music", "family")
      ),
      new Movie(
          "Your Name",
          LocalDate.of(2016, 8, 26),
          Movie.Genre.ANIMATION,
          106,
          8.4,
          Set.of("anime", "romance", "time")
      ),
      new Movie(
          "Free Solo",
          LocalDate.of(2018, 9, 28),
          Movie.Genre.DOCUMENTARY,
          100,
          8.2,
          Set.of("climbing", "inspiring")
      ),
      new Movie(
          "Planet Earth",
          LocalDate.of(2006, 3, 5),
          Movie.Genre.DOCUMENTARY,
          60,
          9.4,
          Set.of("nature", "bbc")
      ),
      new Movie(
          "Oppenheimer",
          LocalDate.of(2023, 7, 21),
          Movie.Genre.DRAMA,
          180,
          8.4,
          Set.of("biography", "history")
      ),
      new Movie(
          "The Social Network",
          LocalDate.of(2010, 10, 1),
          Movie.Genre.DRAMA,
          120,
          7.7,
          Set.of("startup", "tech")
      ),
      new Movie(
          "The Lord of the Rings: The Fellowship of the Ring",
          LocalDate.of(2001, 12, 19),
          Movie.Genre.ACTION,
          178,
          8.8,
          Set.of("fantasy", "epic")
      ),
      new Movie(
          "The Lord of the Rings: The Two Towers",
          LocalDate.of(2002, 12, 18),
          Movie.Genre.ACTION,
          179,
          8.8,
          Set.of("fantasy", "epic")
      ),
      new Movie(
          "The Lord of the Rings: The Return of the King",
          LocalDate.of(2003, 12, 17),
          Movie.Genre.ACTION,
          201,
          9.0,
          Set.of("fantasy", "epic")
      )
  );

  public static List<Movie> getMovies() {
    return MOVIES;
  }
}