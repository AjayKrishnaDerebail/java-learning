package com.main.section.section7;

import com.main.model.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class FunctionExample {

  public static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction = (students, studentPredicate) -> {
    Map<String, Double> studentGradeMap = new HashMap<>();
    students.forEach(student -> {
      if (studentPredicate.test(student)) {
        studentGradeMap.put(student.firstName() + student.lastName(), student.gpa());
      }
    });

    return studentGradeMap;
  };
}
