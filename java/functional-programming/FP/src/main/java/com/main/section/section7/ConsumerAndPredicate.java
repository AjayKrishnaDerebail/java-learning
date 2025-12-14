package com.main.section.section7;

import static com.main.database.StudentDatabase.getAllStudents;

import com.main.model.Student;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerAndPredicate {

  static Predicate<Student> p1 = s -> s.gpa() >= 3.0;

  static Predicate<Student> p2 = s -> s.gpa() < 4.0;

  static BiConsumer<String, List<String>> studentBiConsumer = (name, activities) -> log.info(
      "Name : {} ; Activities: {}", name, activities);


  static Consumer<Student> studentConsumer = (s -> {
    if (p1.and(p2).test(s)) {
      studentBiConsumer.accept(s.firstName(), s.hobbies());
    }
  });

  public static void printName() {
    getAllStudents().forEach(studentConsumer);
  }

}