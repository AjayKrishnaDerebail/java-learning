package com.main.section.section7.function;

import com.main.model.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionExample {

  public static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction =
      (students, studentPredicate) -> {
    Map<String, Double> studentGradeMap = new HashMap<>();
    students.forEach(student -> {
      if (studentPredicate.test(student)) {
        studentGradeMap.put(student.firstName() + student.lastName(), student.gpa());
      }
    });

    return studentGradeMap;
  };

  public static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>>
      biFunctionPurelyFunctional = (students, studentPredicate) ->
        students.stream()
            .filter(studentPredicate)
            .collect(Collectors.toMap(student -> student.firstName() + student.lastName(),
              Student::gpa,
              Math::max
          ));

}