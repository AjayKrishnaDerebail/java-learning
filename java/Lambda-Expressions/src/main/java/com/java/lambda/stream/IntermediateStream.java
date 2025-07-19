package com.java.lambda.stream;

import java.util.Comparator;
import java.util.stream.IntStream;

public class IntermediateStream {

  public static void main(String[] args) {
    IntStream.iterate('A', i -> i <= (int) 'z', i -> i + 1)
        //.filter(i -> Character.toUpperCase(i) > 'E')
        .filter(Character::isLetter)
        .dropWhile(i -> i <= (int) 'E')
        .takeWhile(i -> i <= (int) 'Z')
        .skip(2)
        .boxed()
        .sorted(Comparator.reverseOrder())
        //.limit(10)
        .forEach(i -> System.out.print((char) i.intValue() + " "));

  }
}