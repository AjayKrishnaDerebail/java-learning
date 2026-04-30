package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.Flight;
import com.virtualthreads.trip.dto.FlightReservationRequest;
import com.virtualthreads.trip.dto.FlightReservationResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightSearchServiceClient {

  private final RestClient client;

  public List<Flight> getFlights(String departure, String arrival) {
    return this.client.get()
        .uri("/{departure}/{arrival}", departure, arrival)
        .retrieve()
        .body(new ParameterizedTypeReference<List<Flight>>() {
        });
  }

}