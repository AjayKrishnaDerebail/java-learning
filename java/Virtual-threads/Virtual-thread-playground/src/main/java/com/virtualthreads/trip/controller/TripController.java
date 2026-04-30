package com.virtualthreads.trip.controller;

import com.virtualthreads.trip.dto.FlightReservationResponse;
import com.virtualthreads.trip.dto.TripPlan;
import com.virtualthreads.trip.dto.TripReservationRequest;
import com.virtualthreads.trip.service.TripPlanService;
import com.virtualthreads.trip.service.TripReservationService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trip")
@Slf4j
public class TripController {

  private final TripPlanService planService;
  private final TripReservationService reservationService;

  public TripController(TripPlanService planService,
      TripReservationService reservationService) {
    this.planService = planService;
    this.reservationService = reservationService;
  }

  @GetMapping("{airportCode}")
  public TripPlan planTrip(@PathVariable("airportCode") String airportCode) {
    val startTime = System.currentTimeMillis();
    log.info("Planning trip for airport code: {} and thread name : {} and isVirtual : {}", airportCode,
        Thread.currentThread().getName(),Thread.currentThread().isVirtual());
    var planResponse = planService.getTripPlan(airportCode);
    log.info("Planning service took {} ms", System.currentTimeMillis() - startTime);
    return planResponse;
  }

  @PostMapping("reserve")
  public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request) {
    val startTime = System.currentTimeMillis();
    val reservationResponse = this.reservationService.reserve(request);
    log.info("Reservation service took {} ms", System.currentTimeMillis() - startTime);
    return reservationResponse;
  }

}