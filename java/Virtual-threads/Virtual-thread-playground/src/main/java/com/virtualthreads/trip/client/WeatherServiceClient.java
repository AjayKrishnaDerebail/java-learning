package com.virtualthreads.trip.client;

import com.virtualthreads.trip.dto.Transportation;
import com.virtualthreads.trip.dto.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class WeatherServiceClient {

  private final RestClient client;

  public Weather getWeather(String airportCode) {
    return client.get()
        .uri("{airportCode}", airportCode)
        .retrieve()
        .body(Weather.class);
  }

}