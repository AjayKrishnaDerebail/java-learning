package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.FlightReservationRequest;
import com.virtualthreads.trip.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightReservationServiceClient {

  private final RestClient client;

  public FlightReservationResponse reserve(@PathVariable("airportCode") FlightReservationRequest request) {
    return this.client.post()
        .body(request)
        .retrieve()
        .body(FlightReservationResponse.class);
  }

}