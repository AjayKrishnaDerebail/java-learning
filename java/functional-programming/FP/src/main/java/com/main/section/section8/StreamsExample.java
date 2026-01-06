package com.main.section.section8;

import com.main.model.Student;
import com.main.utility.StudentDatabase;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@SuppressWarnings("unused")
public class StreamsExample {
  public static void simpleStreamExample(){
    var studentHobbiesMap = StudentDatabase.getAllStudents().parallelStream()
        //.peek(System.out::println)
        .filter(s -> s.gpa() >= 4.0)
        .peek(System.out::println)
        .collect(Collectors.toMap(Student::firstName, Student::hobbies));

    //log.info("{}",studentHobbiesMap);
  }

  public static void streamsMapExample(){
    StudentDatabase.getAllStudents().stream()
        .map(s -> s.firstName().toUpperCase() + ' ' + s.lastName())
        .forEach(log::info);
  }

  public static void flatMapExample(){
        val flatMap = StudentDatabase.getAllStudents().stream()
            .map(Student::hobbies)
            .flatMap(List::stream) // This will make it a 1 dimensional List<String>
            .distinct()
            .sorted()
            //.count()
            .toList();

    log.info("{}",flatMap);
  }

  /**
   * Sorts students using a custom comparator
   * @param comparator The sorting logic
   */
  public static void sortStudents(Comparator<Student> comparator) {
    StudentDatabase.getAllStudents()
        .stream()
        .sorted(comparator.reversed())
        .forEach(System.out::println);
  }

  public static void streamReduceExample(double initial, BinaryOperator<Double> binaryOperator){
    val result = StudentDatabase.getAllStudents().stream()
        .map(Student::gpa)
        .reduce(initial, binaryOperator);

    log.info("Result of reduce is : {}",result);
  }

  public static void streamReduceHighestGpa(){
    Optional<Student> result = StudentDatabase.getAllStudents().stream()
        .reduce((s1, s2) -> s1.gpa() > s2.gpa() ? s1 : s2);

    log.info("Result of reduce highest gpa is : {}",result);
  }

}