package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.Event;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class EventServiceClient {

  private final RestClient restClient;

  public List<Event> getEvents(@PathVariable("airportCode") final String airportCode) {

    return restClient.get()
        .uri("{airportCode}", airportCode)
        .retrieve()
        .body(new ParameterizedTypeReference<List<Event>>() {
        });

  }

}