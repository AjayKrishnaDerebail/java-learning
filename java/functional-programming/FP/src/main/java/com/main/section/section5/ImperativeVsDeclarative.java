package com.main.section.section5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class ImperativeVsDeclarative {

  private static final List<Integer> numbers =
      List.of(1, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4, 2, 2, 9, 10);

  // Imperative approach: How to do it
  public static List<Integer> getNonDuplicateListImperativeStyle() {
    val currentTime = System.currentTimeMillis();
    List<Integer> uniqueList = new ArrayList<>();
    for (final var number : numbers) {
      if (!uniqueList.contains(number)) {
        uniqueList.add(number);
      }
    }
    log.info("Imperative approach took {} ms", System.currentTimeMillis() - currentTime);
    return uniqueList;
  }

  public static List<Integer> getNonDuplicateListDeclarativeStyle() {
    val currentTime = System.currentTimeMillis();
    val list = numbers.stream()
        .distinct()
        .collect(Collectors.toList());
    log.info("Declarative approach took {} ms", System.currentTimeMillis() - currentTime);
    return list;
  }
}