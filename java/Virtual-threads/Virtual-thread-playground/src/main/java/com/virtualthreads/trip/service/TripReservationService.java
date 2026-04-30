package com.virtualthreads.trip.service;

import com.virtualthreads.trip.client.FlightReservationServiceClient;
import com.virtualthreads.trip.client.FlightSearchServiceClient;
import com.virtualthreads.trip.dto.Flight;
import com.virtualthreads.trip.dto.FlightReservationRequest;
import com.virtualthreads.trip.dto.FlightReservationResponse;
import com.virtualthreads.trip.dto.TripReservationRequest;
import java.util.Comparator;

public class TripReservationService {

  private final FlightSearchServiceClient searchServiceClient;
  private final FlightReservationServiceClient reservationServiceClient;

  public TripReservationService(FlightSearchServiceClient searchServiceClient,
      FlightReservationServiceClient reservationServiceClient) {
    this.searchServiceClient = searchServiceClient;
    this.reservationServiceClient = reservationServiceClient;
  }

  public FlightReservationResponse reserve(TripReservationRequest request) {
    var flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
    var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
    var flight = bestDeal.orElseThrow(() -> new IllegalStateException("no flights found"));
    var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(),
        flight.flightNumber(), request.date());
    return this.reservationServiceClient.reserve(reservationRequest);
  }

}