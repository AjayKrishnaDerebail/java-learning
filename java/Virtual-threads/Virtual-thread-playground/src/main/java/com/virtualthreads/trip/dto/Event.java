package com.virtualthreads.trip.dto;


import java.time.LocalDate;

public record Event(String name,
                    String description,
                    LocalDate date) {
}