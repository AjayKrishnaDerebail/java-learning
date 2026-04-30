package com.virtualthreads.trip.configuration;

import com.virtualthreads.trip.client.AccommodationServiceClient;
import com.virtualthreads.trip.client.EventServiceClient;
import com.virtualthreads.trip.client.FlightReservationServiceClient;
import com.virtualthreads.trip.client.FlightSearchServiceClient;
import com.virtualthreads.trip.client.LocalRecommendationServiceClient;
import com.virtualthreads.trip.client.TransportationServiceClient;
import com.virtualthreads.trip.client.WeatherServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class ServiceClientsConfig {

  @Value("${spring.threads.virtual.enabled}")
  private boolean isVirtualThreadEnabled;

  @Bean
  public AccommodationServiceClient accommodationServiceClient(
      @Value("${accommodation.service.url}") String baseUrl) {
    return new AccommodationServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public EventServiceClient eventServiceClient(@Value("${event.service.url}") String baseUrl) {
    return new EventServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public WeatherServiceClient weatherServiceClient(
      @Value("${weather.service.url}") String baseUrl) {
    return new WeatherServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public TransportationServiceClient transportationServiceClient(
      @Value("${transportation.service.url}") String baseUrl) {
    return new TransportationServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public LocalRecommendationServiceClient recommendationServiceClient(
      @Value("${local-recommendation.service.url}") String baseUrl) {
    return new LocalRecommendationServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public FlightSearchServiceClient flightSearchServiceClient(
      @Value("${flight-search.service.url}") String baseUrl) {
    return new FlightSearchServiceClient(buildRestClient(baseUrl));
  }

  @Bean
  public FlightReservationServiceClient reservationServiceClient(
      @Value("${flight-reservation.service.url}") String baseUrl) {
    return new FlightReservationServiceClient(buildRestClient(baseUrl));
  }

  private RestClient buildRestClient(String baseUrl) {
    log.info("base url: {}", baseUrl);
    var builder = RestClient.builder().baseUrl(baseUrl);
    if (isVirtualThreadEnabled) {
      builder = builder.requestFactory(new JdkClientHttpRequestFactory(
          HttpClient.newBuilder().executor(Executors.newVirtualThreadPerTaskExecutor()).build()
      ));
    }
    return builder.build();
  }

}
