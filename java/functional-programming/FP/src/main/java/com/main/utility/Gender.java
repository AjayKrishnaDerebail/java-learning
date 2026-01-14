package com.main.utility;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
  M("male"),
  F("female");

  private final String value;

  public String getValue() {
    return value;
  }
}