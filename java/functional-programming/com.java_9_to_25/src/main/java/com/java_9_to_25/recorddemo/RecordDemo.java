package com.java_9_to_25.recorddemo;

import java.time.LocalDate;
import java.util.Objects;

public final class RecordDemo {

  private RecordDemo() {
  }

  public static void run() {
    IO.println("\n--- Record Demo ---");

    demoBasicRecordFeatures();
    demoConstructorsAndValidation();
    demoCustomMethodsAndDerivedState();
    demoImplementsInterface();
    demoNestedRecord();
  }

  private static void demoBasicRecordFeatures() {
    IO.println("\n1) Basic features (components -> fields + accessors, equals/hashCode/toString)");

    var p1 = new Person("Ajay", 25);
    var p2 = new Person("Ajay", 25);

    IO.println("p1.name() = " + p1.name());
    IO.println("p1.age()  = " + p1.age());
    IO.println("p1.toString() = " + p1);
    IO.println("p1.equals(p2) = " + p1.equals(p2));
    IO.println("p1.hashCode() == p2.hashCode() = " + (p1.hashCode() == p2.hashCode()));
  }

  private static void demoConstructorsAndValidation() {
    IO.println("\n2) Compact constructor for validation (invariants)");

    try {
      new Person("", 25);
    } catch (IllegalArgumentException e) {
      IO.println("Expected validation error: " + e.getMessage());
    }

    try {
      new Person("Valid Name", -1);
    } catch (IllegalArgumentException e) {
      IO.println("Expected validation error: " + e.getMessage());
    }
  }

  private static void demoCustomMethodsAndDerivedState() {
    IO.println("\n3) Custom instance methods + derived values");

    var pointA = new Point(3, 4);
    IO.println("pointA = " + pointA);
    IO.println("pointA.magnitude() = " + pointA.magnitude());

    var pointB = pointA.translate(10, -2);
    IO.println("pointB (translated) = " + pointB);

    var pointC = pointA.translate(11, -4);
    IO.println("pointC (translated) = " + pointC);
  }

  private static void demoImplementsInterface() {
    IO.println("\n4) A record can implement interfaces");

    IdentifiedEvent event = new UserSignedUp("evt-1", LocalDate.of(2026, 2, 1), "Ajay");
    IO.println("event.id() = " + event.id());
    IO.println("event.occurredOn() = " + event.occurredOn());
    IO.println("event = " + event);
  }

  private static void demoNestedRecord() {
    IO.println("\n5) Nested records are great for small value aggregates");

    var address = new Customer.Address("Bengaluru", "KA", "560001");
    var customer = new Customer("customer-1", "Ajay", address);

    IO.println("customer = " + customer);
    IO.println("customer.address().city() = " + customer.address().city());
  }
}

record Person(String name, int age) {
  Person {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name must be non-blank");
    }
    if (age < 0) {
      throw new IllegalArgumentException("age must be >= 0");
    }
  }
}

record Point(int x, int y) {
  double magnitude() {
    return Math.sqrt((x * (double) x) + (y * (double) y));
  }

  Point translate(int dx, int dy) {
    return new Point(x + dx, y + dy);
  }
}

interface IdentifiedEvent {
  String id();

  LocalDate occurredOn();
}

record UserSignedUp(String id, LocalDate occurredOn, String username) implements IdentifiedEvent {
  UserSignedUp {
    Objects.requireNonNull(id, "id");
    Objects.requireNonNull(occurredOn, "occurredOn");
    Objects.requireNonNull(username, "username");
  }
}

record Customer(String customerId, String name, Address address) {

  record Address(String city, String state, String zip) {
    Address {
      Objects.requireNonNull(city, "city");
      Objects.requireNonNull(state, "state");
      Objects.requireNonNull(zip, "zip");
    }
  }
}