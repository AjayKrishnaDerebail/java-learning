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
   * Executes a high-concurrency, non-blocking scatter-gather pipeline to reconstruct
   * {@link Movie} objects from multiple asynchronous data sources.
   *
   * <p><b>Technical Execution Stages:</b>
   *
   * <h3>1. The Async Dispatch (The Scatter Phase)</h3>
   * For each identifier in {@code movieInfoIds}, the pipeline initiates a
   * <b>Fork-Join</b> sequence. Using {@code supplyAsync}, two independent tasks
   * are dispatched to the {@code ForkJoinPool.commonPool()}.
   * <ul>
   * <li><b>Task A:</b> Execution of {@code invokeMovieInfoService(movieId)}.</li>
   * <li><b>Task B:</b> Execution of {@code invokeReviewsInfoService(movieId)}.</li>
   * </ul>
   * This phase offloads I/O-intensive work from the calling thread, potentially
   * saturating available worker threads to minimize overall latency.
   *
   * <h3>2. Binary Task Fusion (Internal Synchronization)</h3>
   * The {@code thenCombine} operator functions as a <b>Dependency Node</b> in the
   * execution graph. It manages internal synchronization by ensuring that the
   * {@link Movie} constructor is only invoked once both Task A and Task B transition
   * to the {@code COMPLETED} state. This prevents partial object construction
   * and eliminates the need for manual lock management or "wait-and-notify" logic.
   *
   * <h3>3. Aggregate Barrier (The allOf Synchronization)</h3>
   * To handle the collection of individual pipelines, {@link CompletableFuture#allOf}
   * is utilized as a <b>Synchronization Barrier</b>. It transforms a {@code List}
   * of disparate futures into a single aggregate "Master Future." This master
   * future only resolves when every individual movie future in the stream has reached
   * a terminal state (Success or Failure).
   *
   * <h3>4. Non-Blocking Data Gathering</h3>
   * Upon the resolution of the {@code allOf} barrier, the {@code thenApply} block
   * initiates the "Gather" phase. While {@link CompletableFuture#join()} is
   * technically a blocking call, it is <b>non-blocking in this context</b> because
   * the preceding barrier guarantees that all data is already available in memory.
   * The final terminal {@code join()} is the only point where the calling thread
   * suspends execution.
   *
   * <p><b>Threading Considerations:</b>
   * This method is sensitive to <b>Common Pool Saturation</b>. If the input list
   * size exceeds the available parallelism of the {@code ForkJoinPool}, tasks will
   * be queued, increasing latency. For high-volume production environments,
   * consider passing a custom {@code Executor} to the {@code supplyAsync} calls
   * to isolate these I/O tasks.
   * {@code @movieInfoIds} A list of IDs to be processed; size directly impacts
   * worker thread demand.
   * @return A list of reconstructed Movie objects.
   * {@code @throwsCompletionException} if any internal future fails during execution.
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