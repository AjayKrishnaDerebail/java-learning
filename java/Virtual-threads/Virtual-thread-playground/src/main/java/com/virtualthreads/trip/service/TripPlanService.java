package com.virtualthreads.trip.service;

import com.virtualthreads.trip.client.AccommodationServiceClient;
import com.virtualthreads.trip.client.EventServiceClient;
import com.virtualthreads.trip.client.LocalRecommendationServiceClient;
import com.virtualthreads.trip.client.TransportationServiceClient;
import com.virtualthreads.trip.client.WeatherServiceClient;
import com.virtualthreads.trip.dto.TripPlan;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TripPlanService {

  private final EventServiceClient eventServiceClient;
  private final WeatherServiceClient weatherServiceClient;
  private final AccommodationServiceClient accommodationServiceClient;
  private final TransportationServiceClient transportationServiceClient;
  private final LocalRecommendationServiceClient localRecommendationServiceClient;
  private final ExecutorService executor;

  public TripPlanService(EventServiceClient eventServiceClient,
      WeatherServiceClient weatherServiceClient,
      AccommodationServiceClient accommodationServiceClient,
      TransportationServiceClient transportationServiceClient,
      LocalRecommendationServiceClient localRecommendationServiceClient, ExecutorService executor) {
    this.eventServiceClient = eventServiceClient;
    this.weatherServiceClient = weatherServiceClient;
    this.accommodationServiceClient = accommodationServiceClient;
    this.transportationServiceClient = transportationServiceClient;
    this.localRecommendationServiceClient = localRecommendationServiceClient;
    this.executor = executor;
  }

  public TripPlan getTripPlan(String airportCode) {
    var events = this.executor.submit(() -> this.eventServiceClient.getEvents(airportCode));
    var weather = this.executor.submit(() -> this.weatherServiceClient.getWeather(airportCode));
    var accommodations = this.executor.submit(
        () -> this.accommodationServiceClient.getAccommodations(airportCode));
    var transportation = this.executor.submit(
        () -> this.transportationServiceClient.getTransportation(airportCode));
    var recommendations = this.executor.submit(
        () -> this.localRecommendationServiceClient.getRecommendations(airportCode));
    return new TripPlan(
        airportCode,
        getOrElse(accommodations, Collections.emptyList()),
        getOrElse(weather, null),
        getOrElse(events, Collections.emptyList()),
        getOrElse(recommendations, null),
        getOrElse(transportation, null)
    );
  }

  private <T> T getOrElse(Future<T> future, T defaultValue) {
    try {
      return future.get();
    } catch (Exception e) {
      log.error("error", e);
    }
    return defaultValue;
  }

}