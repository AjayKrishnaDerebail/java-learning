package com.java.lambda;


public class Main {

  public static void main(String[] args) {
    MyInterface myLambda = () -> System.out.println("Hello and welcome! ");

    myLambda.myMethod();

    MyInterfaceAdd myLambdaAdd = Integer::sum;

    System.out.println(myLambdaAdd.myMethodAdd(1, 2));

    Greeting greeting = new Greeting(){
      public void myMethod() {
        System.out.println("Hello World!");
      }
    };

    greeting.myMethod();

    StringLengthLambda stringLengthLambda = String :: length;

    System.out.println(stringLengthLambda.getStringLength("Hello World!"));

    printStringLengthLambda(stringLengthLambda, "Hello World!");
  }

  public static void printStringLengthLambda(StringLengthLambda str , String str1) {
    System.out.println(str.getStringLength(str1));
    System.out.println(str.getCharLength(str1));
  }
}

interface MyInterface {
    void myMethod();
}

interface MyInterfaceAdd {
    int myMethodAdd(int a, int b);
}

abstract class Greeting {
  public void myMethod() {
  }
}

@FunctionalInterface
interface StringLengthLambda {
  int getStringLength(String str);

  default int getCharLength(String str) {
    return str.length();
  }
}