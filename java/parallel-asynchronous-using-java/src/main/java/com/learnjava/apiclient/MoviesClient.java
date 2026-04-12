package com.learnjava.apiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import java.util.List;
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
    val movie = moviesClient.retrieveMovieInfo(1L);

    log.info("Movie is : {} ", movie);
  }

  public Movie retrieveMovieInfo(final Long movieInfoId) {
    val movieInfo = invokeMovieInfoService(movieInfoId);
    val reviews = invokeReviewsInfoService(movieInfoId);
    return new Movie(movieInfo, reviews);
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