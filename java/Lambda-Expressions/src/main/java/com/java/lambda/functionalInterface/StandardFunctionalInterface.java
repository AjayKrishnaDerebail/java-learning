package com.java.lambda.functionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import lombok.val;

public class StandardFunctionalInterface {

  public static void main(String[] args) {

    // 🔹 1. Data Setup
    List<Person> personList = new ArrayList<>(
        Arrays.asList(
            new Person("John", "Taker"),
            new Person("Jane", "Doe"),
            new Person("John", "Smith"),
            new Person("Jane", "Smith"),
            new Person("Undertaker", "Doe"),
            new Person("Kane", "Doe"))
    );

    // 🔹 2. Sorting using lambda comparator
    personList.sort(Comparator.comparing(Person::getLastName));

    // 🔹 3. Filtering using Predicate and Consumer combinations
    System.out.println("\n🔹 Print All:");
    printConditionally(personList, p -> true, System.out::println);

    System.out.println("\n🔹 Print where last name starts with 'D':");
    printConditionally(personList, p -> p.getLastName().startsWith("D"), System.out::println);

    System.out.println("\n🔹 Print where last name ends with 'e' AND first name starts with 'J':");
    printConditionally(personList,
        p -> p.getLastName().endsWith("e") && p.getFirstName().startsWith("J"),
        System.out::println);

    // 🔹 4. Using forEach directly on a list
    System.out.println("\n🔹 Print all using forEach:");
    personList.forEach(System.out::println);

    // 🔹 5. Removing elements using Predicate (removeIf)
    System.out.println("\n🔹 Removing people with last name starting with 'D':");
    personList.removeIf(p -> p.getLastName().startsWith("D"));
    personList.forEach(System.out::println);

    // 🔹 6. Functional Interfaces in Action
    System.out.println("\n🔹 Functional Interface Implementations:");

    // Traditional class implementation
    class AddOperation implements Operation<Integer> {

      @Override
      public Integer execute(Integer a, Integer b) {
        return a + b;
      }
    }

    Operation<Integer> addOperation = new AddOperation();
    int result1 = executeOperation(addOperation, 1, 2);  // 1 + 2 = 3
    System.out.println("Using traditional class: " + result1);

    // Anonymous inner class
    int result2 = executeOperation(new Operation<>() {
      @Override
      public Integer execute(Integer a, Integer b) {
        return a - b;
      }
    }, 10, 2);  // 10 - 2 = 8
    System.out.println("Using anonymous inner class: " + result2);

    // Lambda expression
    int result3 = executeOperation(Integer::sum, 1, 2);  // 1 + 2 = 3
    System.out.println("Using lambda: " + result3);

    // 🔹 7. BinaryOperator Examples
    System.out.println("\n🔹 Using BinaryOperator Functional Interface:");

    // String concatenation with case transformation
    String result4 = executeOperationUsingBinaryOperator(
        (a, b) -> a.toLowerCase() + b.toUpperCase(), "Hello", "World");
    System.out.println("String concat: " + result4);  // helloWORLD

    // Arithmetic division with double values
    val result5 = executeOperationUsingBinaryOperator((a, b) -> a / b, 9.0, 2.0);
    System.out.println("Division result: " + result5);  // 4.5

  }

  // 🔹 Utility method: generic conditional printer using Predicate and Consumer
  public static void printConditionally(List<Person> personList, Predicate<Person> predicate,
      Consumer<Person> consumer) {
    for (Person person : personList) {
      if (predicate.test(person)) {
        consumer.accept(person);
      }
    }
  }

  // 🔹 Generic method to demonstrate custom functional interface
  public static <T> T executeOperation(Operation<T> operation, T a, T b) {
    return operation.execute(a, b);
  }

  // 🔹 Generic method using BinaryOperator (standard Java functional interface)
  public static <T> T executeOperationUsingBinaryOperator(BinaryOperator<T> operation, T a, T b) {
    return operation.apply(a, b);
  }
}

// 🔹 Custom functional interface for arithmetic or logic operations
@FunctionalInterface
interface Operation<T> {

  T execute(T a, T b);
}