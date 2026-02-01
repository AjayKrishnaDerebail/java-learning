package com.java_9_to_25;

import com.java_9_to_25.gatherers.StreamGatherersMainClass;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    String name = "Ajay";

    final var nameMain = List.of("Dilip");
    IO.println(nameMain);
    IO.println(name + ", hello and welcome!");

    final var multiLineString = """
        This is a
          multiline String
        This is awesome !!!
        """;

    /*IO.print(multiLineString);*/

    /*IO.println(NewSwitch.getDays(Month.FEBRUARY, 1900));*/

    /*RecordDemo.run();*/

    /*SealedDemo.run();*/

    StreamGatherersMainClass.streamGatherersDemo();

  }

}