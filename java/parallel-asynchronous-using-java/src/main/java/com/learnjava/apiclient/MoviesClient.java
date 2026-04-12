package com.learnjava.apiclient;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class MoviesClient {

  private final WebClient webClient = createWebClient();

  private static WebClient createWebClient() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    ExchangeStrategies strategies = ExchangeStrategies.builder()
        .codecs(configurer -> {
          configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
          configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
        })
        .build();

    return WebClient.builder()
        .baseUrl("http://localhost:8080/movies")
        .exchangeStrategies(strategies)
        .build();
  }

  static void main() {
    MoviesClient moviesClient = new MoviesClient();
    startTimer();
    val movie = moviesClient.retrieveMovieInfo(1L);
    timeTaken();
    log.info("Movie is : {} ", movie);

    stopWatchReset();

    startTimer();
    val movieCF = moviesClient.retrieveMovieInfoCF(2L);
    timeTaken();
    log.info("Movie is : {} ", movieCF);

    stopWatchReset();

    startTimer();
    val moviesList = moviesClient.retrieveMoviesListInfoCF(List.of(1L, 2L, 3L));
    timeTaken();
    log.info("Movies list size: {}, Movies: {}", moviesList.size(), moviesList);

  }

  public Movie retrieveMovieInfo(final Long movieInfoId) {
    val movieInfo = invokeMovieInfoService(movieInfoId);
    val reviews = invokeReviewsInfoService(movieInfoId);
    return new Movie(movieInfo, reviews);
  }


  /**
   * Retrieves a single movie's info and reviews in parallel using CompletableFuture.
   *
   * <p>Uses supplyAsync to fetch movie info and reviews concurrently,
   * then combines them using thenCombine into a Movie object.
   *
   * @param movieInfoId the movie ID to fetch
   * @return Movie object with populated info and reviews
   */
  public Movie retrieveMovieInfoCF(final Long movieInfoId) {
    val movieInfoFuture = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
    val reviewsFuture = CompletableFuture.supplyAsync(() -> invokeReviewsInfoService(movieInfoId));

    return movieInfoFuture.
        thenCombine(reviewsFuture, Movie::new)
        .join();
  }

  /**
   * Retrieves a list of movies in parallel using CompletableFuture.
   *
   * <p>For each movie ID, this method concurrently fetches:
   * <ul>
   *   <li>Movie info (name, year, cast, release date)</li>
   *   <li>Reviews for that movie</li>
   * </ul>
   *
   * <p>Execution flow:
   * <ol>
   *   <li>Creates a CompletableFuture for each movie ID that fetches both movie info
   *       and reviews asynchronously in parallel (using thenCombine)</li>
   *   <li>Uses CompletableFuture.allOf() to wait for all futures to complete</li>
   *   <li>Collects all results into a List<Movie></li>
   * </ol>
   *
   * @param movieInfoIds list of movie IDs to fetch
   * @return list of Movie objects with populated info and reviews
   */
  public List<Movie> retrieveMoviesListInfoCF(final List<Long> movieInfoIds) {
    val movieFutures = movieInfoIds.stream()
        .map(movieId -> CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieId))
            .thenCombine(
                CompletableFuture.supplyAsync(() -> invokeReviewsInfoService(movieId)),
                Movie::new))
        .toList();

    val allMoviesFuture = CompletableFuture.allOf(
            movieFutures.toArray(new CompletableFuture[0]))
        .thenApply(_ -> movieFutures.stream()
            .map(CompletableFuture::join)
            .toList());

    return allMoviesFuture.join();
  }

  private MovieInfo invokeMovieInfoService(final Long movieInfoId) {
    var moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";

    return webClient.get()
        .uri(moviesInfoUrlPath, movieInfoId)
        .retrieve()
        .bodyToMono(MovieInfo.class)
        .block();
  }

  private List<Review> invokeReviewsInfoService(final Long movieInfoId) {

    @SuppressWarnings("VulnerableCodeUsages")
    var reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
        .queryParam("movieInfoId", movieInfoId)
        .buildAndExpand()
        .toString();

    return webClient.get()
        .uri(reviewUri)
        .retrieve()
        .bodyToFlux(Review.class)
        .collectList()
        .block();
  }

}