package com.java_9_to_25.sealeddemo;

public final class SealedDemo {

  private SealedDemo() {
  }

  public static void run() {
    IO.println("\n--- Sealed Demo ---");

    demoSealedClassHierarchy();
    demoSealedInterfaceWithDefaultMethod();
    demoNestedStaticSealedTypes();
    demoSealedInterfaceWithRecords();
  }

  private static void demoSealedClassHierarchy() {
    IO.println("\n1) sealed class + permits + final/non-sealed/abstract subclasses");

    Shape s1 = new Circle(10);
    Shape s2 = new Rectangle(4, 5);
    Shape s3 = new ColoredShape("red", new Circle(2));

    IO.println("describe(s1) = " + describe(s1));
    IO.println("describe(s2) = " + describe(s2));
    IO.println("describe(s3) = " + describe(s3));
  }

  private static String describe(Shape shape) {
    return switch (shape) {
      case Circle c -> "Circle(radius=" + c.radius() + ")";
      case Rectangle r -> "Rectangle(w=" + r.width() + ", h=" + r.height() + ")";
      case ColoredShape cs -> "Colored(" + cs.color() + ", " + describe(cs.inner()) + ")";
    };
  }

  private static void demoSealedInterfaceWithDefaultMethod() {
    IO.println("\n2) sealed interface + default method + implementing types");

    Account a1 = new SavingsAccount("A-1", 1000);
    Account a2 = new CorporateAccount("A-2", 5000, "Acme");

    IO.println(a1.summary());
    IO.println(a2.summary());
  }

  private static void demoNestedStaticSealedTypes() {
    IO.println("\n3) static nested sealed types");

    var ok = new ApiResult.Ok<>("done");
    var err = new ApiResult.Error(500, "boom");

    IO.println("ok = " + ok);
    IO.println("err = " + err);

    IO.println("render(ok) = " + ApiResult.render(ok));
    IO.println("render(err) = " + ApiResult.render(err));
  }

  private static void demoSealedInterfaceWithRecords() {
    IO.println("\n4) sealed + record (very common combo)");

    Expr expr = new Add(new Lit(40), new Neg(new Lit(2)));
    IO.println("expr = " + expr);
    IO.println("eval(expr) = " + Expr.eval(expr));
  }
}

// -----------------------------------------------------------------------------
// Sealed + record
// -----------------------------------------------------------------------------

sealed interface Expr permits Lit, Add, Neg {

  static int eval(Expr expr) {
    return switch (expr) {
      case Lit lit -> lit.value();
      case Add add -> eval(add.left()) + eval(add.right());
      case Neg neg -> -eval(neg.inner());
    };
  }
}

record Lit(int value) implements Expr {
}

record Add(Expr left, Expr right) implements Expr {
}

record Neg(Expr inner) implements Expr {
}

// -----------------------------------------------------------------------------
// Sealed class hierarchy
// -----------------------------------------------------------------------------

sealed abstract class Shape permits Circle, Rectangle, ColoredShape {
}

// A permitted subtype can be final (cannot be extended further)
final class Circle extends Shape {
  private final int radius;

  Circle(int radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("radius must be > 0");
    }
    this.radius = radius;
  }

  int radius() {
    return radius;
  }
}

// A permitted subtype can be non-sealed (re-opens the hierarchy below it)
non-sealed class Rectangle extends Shape {
  private final int width;
  private final int height;

  Rectangle(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width/height must be > 0");
    }
    this.width = width;
    this.height = height;
  }

  int width() {
    return width;
  }

  int height() {
    return height;
  }
}

// Another permitted subtype; shown as final as well.
final class ColoredShape extends Shape {
  private final String color;
  private final Shape inner;

  ColoredShape(String color, Shape inner) {
    if (color == null || color.isBlank()) {
      throw new IllegalArgumentException("color must be non-blank");
    }
    this.color = color;
    this.inner = inner;
  }

  String color() {
    return color;
  }

  Shape inner() {
    return inner;
  }
}

// -----------------------------------------------------------------------------
// Sealed interface + default methods
// -----------------------------------------------------------------------------

sealed interface Account permits SavingsAccount, CorporateAccount {

  String id();

  long balance();

  default String summary() {
    return "Account[id=" + id() + ", balance=" + balance() + "]";
  }
}

final class SavingsAccount implements Account {
  private final String id;
  private final long balance;

  SavingsAccount(String id, long balance) {
    this.id = id;
    this.balance = balance;
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public long balance() {
    return balance;
  }
}

final class CorporateAccount implements Account {
  private final String id;
  private final long balance;
  private final String company;

  CorporateAccount(String id, long balance, String company) {
    this.id = id;
    this.balance = balance;
    this.company = company;
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public long balance() {
    return balance;
  }

  @SuppressWarnings("unused")
  String company() {
    return company;
  }

  @Override
  public String summary() {
    return "CorporateAccount[id=" + id() + ", company=" + company + ", balance=" + balance + "]";
  }
}

// -----------------------------------------------------------------------------
// Nested static sealed types
// -----------------------------------------------------------------------------

final class ApiResult {

  private ApiResult() {
  }

  sealed interface Result<T> permits Ok, Error {
  }

  static final class Ok<T> implements Result<T> {
    private final T value;

    Ok(T value) {
      this.value = value;
    }

    T value() {
      return value;
    }

    @Override
    public String toString() {
      return "Ok[value=" + value + "]";
    }
  }

  static final class Error implements Result<Void> {
    private final int code;
    private final String message;

    Error(int code, String message) {
      this.code = code;
      this.message = message;
    }

    int code() {
      return code;
    }

    String message() {
      return message;
    }

    @Override
    public String toString() {
      return "Error[code=" + code + ", message=" + message + "]";
    }
  }

  static String render(Result<?> result) {
    return switch (result) {
      case Ok<?> ok -> "SUCCESS: " + ok.value();
      case Error err -> "FAIL: " + err.code() + " / " + err.message();
    };
  }
}

// -----------------------------------------------------------------------------
// What fails to compile (uncomment one at a time to see compiler errors)
// -----------------------------------------------------------------------------

/*

1) A direct subtype of a sealed type must be final, sealed, or non-sealed

sealed class Vehicle permits Car {
}

// ERROR: class is not allowed to extend sealed class: must be final/sealed/non-sealed
class Car extends Vehicle {
}

2) A type not listed in permits cannot extend/implement the sealed type

sealed interface Payment permits CardPayment {
}

final class CardPayment implements Payment {
}

// ERROR: not in permits list
final class CashPayment implements Payment {
}

3) You cannot put random/unrelated types in permits

sealed class A permits String {
  // ERROR: String is final and does not extend A
}

4) sealed is about controlling DIRECT subtypes, not transitive ones
   If you mark a permitted subtype as non-sealed, anyone can extend it.

sealed class Base permits Open {
}

non-sealed class Open extends Base {
}

class AnyOneCanExtend extends Open {
}

*/
