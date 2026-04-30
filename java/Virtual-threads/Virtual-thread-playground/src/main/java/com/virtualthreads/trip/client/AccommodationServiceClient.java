package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.Accommodation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AccommodationServiceClient {

  private final RestClient restClient;

  public List<Accommodation> getAccommodations(final String airportCode) {

    return restClient.get()
        .uri("{airportCode}", airportCode)
        .retrieve()
        .body(new ParameterizedTypeReference<List<Accommodation>>() {
        });

  }

}