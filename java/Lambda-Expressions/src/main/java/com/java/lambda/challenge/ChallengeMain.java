package com.java.lambda.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChallengeMain {

  private static final Random random = new Random();

  public static void main(String[] args) {
    String[] names = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary", "Henry"};

    Arrays.setAll(names, i -> names[i].toUpperCase());

    System.out.println(Arrays.toString(names));

    System.out.println("Random name: " + names[random.nextInt(names.length)]);

    List<String> backedByArray = new ArrayList<>(Arrays.asList(names));

    System.out.println("Randomly adding a character to each name: " );

    backedByArray.replaceAll(s -> s + " " + getRandomChar('B', 'Y') + ".");

    backedByArray.forEach(System.out::println);

    System.out.println("Reversing each name and adding it to the end: " );

    backedByArray.replaceAll(s -> s + " " + reverseString(s.split(" ")[0]));

    backedByArray.forEach(System.out::println);

    System.out.println("Removing names that start and end with the same first and last name: " );

    backedByArray.removeIf(
        s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));

    backedByArray.forEach(System.out::println);
  }

  private static char getRandomChar(char start, char end) {
    return (char) (random.nextInt(start,end+1));
  }

  private static String reverseString(String firstName) {
    return new StringBuilder(firstName).reverse().toString();
  }
}
