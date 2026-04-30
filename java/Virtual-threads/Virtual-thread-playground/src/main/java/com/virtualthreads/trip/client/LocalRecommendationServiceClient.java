package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.LocalRecommendations;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class LocalRecommendationServiceClient {

  private final RestClient client;

  public LocalRecommendations getRecommendations(@PathVariable("airportCode") String airportCode) {
    return client.get()
        .uri("{airportCode}", airportCode)
        .retrieve()
        .body(LocalRecommendations.class);
  }

}