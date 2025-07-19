package com.java.lambda.stream;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    Course pymc = new Course("PYMC", "Python Masterclass");
    Course jmc = new Course("JMC", "Java Masterclass");
    Student tim = new Student("AU", 2019, 30, "M",
        true, jmc, pymc);
    System.out.println(tim);

    tim.watchLecture("JMC", 10, 5, 2019);
    tim.watchLecture("PYMC", 7, 7, 2020);
    System.out.println(tim);

    Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
        .limit(10)
        .forEach(System.out::println);
  }
}