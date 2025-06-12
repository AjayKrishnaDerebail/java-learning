package com.java.lambda.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StandardFunctionalInterface {

  public static void main(String[] args) {
    List<Person> personList = Arrays.asList(
        new Person("John", "Taker"),
        new Person("Jane", "Doe"),
        new Person("John", "Smith"),
        new Person("Jane", "Smith"),
        new Person("Undertaker", "Doe"),
        new Person("Kane", "Doe")
    );

    personList.sort((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

    System.out.println("Printing everything");
    printConditionally(personList, p -> true, System.out::println);

    System.out.println("Printing Only ends with");
    printConditionally(personList, p -> p.getLastName().startsWith("D"), System.out::println);

    System.out.println("Printing ends with and starts with");
    printConditionally(personList,
        p -> p.getLastName().endsWith("e") && p.getFirstName().startsWith("J"),
        System.out::println);

    System.out.println("Printing using for each loop");

    personList.forEach(System.out::println);

  }

  public static void printConditionally(List<Person> personList, Predicate<Person> predicate,
      Consumer<Person> consumer) {
    for (Person person : personList) {
      if (predicate.test(person)) {
        consumer.accept(person);
      }
    }
  }

}