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
  }

  public Movie retrieveMovieInfo(final Long movieInfoId) {
    val movieInfo = invokeMovieInfoService(movieInfoId);
    val reviews = invokeReviewsInfoService(movieInfoId);
    return new Movie(movieInfo, reviews);
  }


  public Movie retrieveMovieInfoCF(final Long movieInfoId) {
    val movieInfoFuture = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
    val reviewsFuture = CompletableFuture.supplyAsync(() -> invokeReviewsInfoService(movieInfoId));

    return movieInfoFuture.
        thenCombine(reviewsFuture, Movie::new)
        .join();
  }

  private MovieInfo invokeMovieInfoService(final Long movieInfoId) {
    var moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";

    return webClient.get()
        .uri(moviesInfoUrlPath,movieInfoId)
        .retrieve()
        .bodyToMono(MovieInfo.class)
        .block();
  }

  private List<Review> invokeReviewsInfoService(final Long movieInfoId) {

    @SuppressWarnings("VulnerableCodeUsages")
    var reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
        .queryParam("movieInfoId",movieInfoId)
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