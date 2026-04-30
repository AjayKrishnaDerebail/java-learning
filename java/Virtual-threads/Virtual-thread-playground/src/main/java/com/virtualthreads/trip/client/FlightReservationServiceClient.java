package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.FlightReservationRequest;
import com.virtualthreads.trip.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightReservationServiceClient {

  private final RestClient client;

  public FlightReservationResponse reserve(FlightReservationRequest request) {
    return this.client.post()
        .body(request)
        .retrieve()
        .body(FlightReservationResponse.class);
  }

}