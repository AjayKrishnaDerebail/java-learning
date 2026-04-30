package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.Transportation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class TransportationServiceClient {

  private final RestClient client;

  public Transportation getTransportation(@PathVariable("airportCode") String airportCode) {
    return client.get()
        .uri("{airportCode}", airportCode)
        .retrieve()
        .body(Transportation.class);
  }

}