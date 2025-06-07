package com.java.lambda;


public class Main {

  public static void main(String[] args) {
    MyInterface myLambda = () -> System.out.println("Hello and welcome! ");

    myLambda.myMethod();

    MyInterfaceAdd myLambdaAdd = (int a, int b) -> a + b;

    System.out.println(myLambdaAdd.myMethodAdd(1, 2));

    Greeting greeting = new Greeting(){
      public void myMethod() {
        System.out.println("Hello World!");
      }
    };

    greeting.myMethod();
  }
}

interface MyInterface {
    void myMethod();
}

interface MyInterfaceAdd {
    int myMethodAdd(int a, int b);
}

class Greeting {

  public void myMethod() {
  }
}