package com.java.lambda.functionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.val;

public class StandardFunctionalInterface {

  public static void main(String[] args) {
    List<Person> personList = new ArrayList<>(Arrays.asList(
        new Person("John", "Taker"),
        new Person("Jane", "Doe"),
        new Person("John", "Smith"),
        new Person("Jane", "Smith"),
        new Person("Undertaker", "Doe"),
        new Person("Kane", "Doe")
    ));

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

    System.out.println("Removed objects using predicate");

    personList.removeIf((p) -> p.getLastName().startsWith("D"));

    personList.forEach(System.out::println);

    System.out.println("Understanding lambda expressions better");

    class AddOperation implements Operation<Integer> {

      @Override
      public Integer execute(Integer a, Integer b) {
        return a + b;
      }
    }

    Operation<Integer> addOperation = new AddOperation();
    int result1 = executeOperation(addOperation, 1, 2);

    System.out.println(result1);

    int result2 = executeOperation(new Operation<>() {
      @Override
      public Integer execute(Integer a, Integer b) {
        return a - b;
      }
    },10,2);

    System.out.println(result2);

    int result3 = executeOperation((a, b) -> a + b, 1, 2);

    System.out.println(result3);

    System.out.println("Using binary operator String concatenation");

    String result4 = executeOperationUsingBinaryOperator(
        (a, b) -> a.toLowerCase() + b.toUpperCase(), "Hello", "World");

    System.out.println(result4);

    System.out.println("Using binary operator arithmetic division");

    val result5 = executeOperationUsingBinaryOperator((a,b)-> a / b , 9.0 ,2.0);

    System.out.println(result5);

  }

  public static void printConditionally(List<Person> personList, Predicate<Person> predicate,
      Consumer<Person> consumer) {
    for (Person person : personList) {
      if (predicate.test(person)) {
        consumer.accept(person);
      }
    }
  }

  public static <T> T executeOperation(Operation<T> operation, T a, T b) {
    return operation.execute(a, b);
  }

  public static <T> T executeOperationUsingBinaryOperator(BinaryOperator<T> operation, T a, T b) {
    return operation.apply(a, b);
  }
}

@FunctionalInterface
interface Operation<T> {

  T execute(T a, T b);
}