package com.main.section.section7.function;

import com.main.utility.StudentDatabase;
import com.main.model.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionChallengeGpt {

  static Predicate<Student> honourStudent = s -> s.gpa() >= 4.0;

  static Predicate<Student> goodStudent = s -> s.gpa() >= 3.0 && s.gpa() < 4.0;

  public static Function<Student, String> fullNameFunction =
      s -> s.firstName() + " " + s.lastName();

  public static Function<Student, String> gradeFunction =
      s -> honourStudent.test(s) ? "HONOR"
          : goodStudent.test(s)   ? "GOOD"
              : "PROBATION";


  public static Function<Student, Integer> hobbyCountFunction =
      s -> s.hobbies().size();

  static Function<Student, String> studentSummaryFunction =
      student -> {
        String name = fullNameFunction.apply(student);
        String grade = gradeFunction.apply(student);
        int hobbies = hobbyCountFunction.apply(student);
        double gpa = student.gpa();

        return name + " - " + grade + " - " + hobbies + " - " + gpa;
      };

  static UnaryOperator<Student> normalizeName =
      student -> new Student(
          student.firstName().trim().toUpperCase(),
          student.lastName().trim().toUpperCase(),
          student.gpa(),
          student.hobbies()
      );

  // 2. Boost GPA safely
  static UnaryOperator<Student> gpaBoost =
      student -> new Student(
          student.firstName(),
          student.lastName(),
          Math.min(student.gpa() + 0.2, 4.5),
          student.hobbies()
      );

  // 3. Fix probation students
  static UnaryOperator<Student> probationFixer =
      student -> {
        if (student.gpa() < 3.0) {
          return new Student(
              student.firstName(),
              student.lastName(),
              3.0,
              student.hobbies()
          );
        }
        return student;
      };

  /* =========================
     BINARY OPERATOR EXAMPLES
     ========================= */

  // 5. Pick student with higher GPA
  static BinaryOperator<Student> higherGpa =
      (s1, s2) -> s1.gpa() >= s2.gpa() ? s1 : s2;

  // 6. Merge two students' hobbies
  static BinaryOperator<Student> mergeHobbies =
      (s1, s2) -> {
        List<String> merged = new ArrayList<>();
        merged.addAll(s1.hobbies());
        merged.addAll(s2.hobbies());

        return new Student(
            s1.firstName(),
            s1.lastName(),
            Math.max(s1.gpa(), s2.gpa()),
            merged
        );
      };

  // 7. Cleaner BinaryOperator using Comparator
  static BinaryOperator<Student> bestStudent =
      BinaryOperator.maxBy(Comparator.comparingDouble(Student::gpa));

  public static void functionChallengeDemo() {
    log.info("\n--- Full Names ---");
    StudentDatabase.getAllStudents().stream()
        .map(FunctionChallengeGpt.fullNameFunction)
        .forEach(log::info);

    log.info("\n--- Student Grades ---");
    StudentDatabase.getAllStudents().stream()
        .map(student -> String.format("%s: %s",
            FunctionChallengeGpt.fullNameFunction.apply(student),
            FunctionChallengeGpt.gradeFunction.apply(student)))
        .forEach(log::info);

    log.info("\n--- Student Summaries ---");
    StudentDatabase.getAllStudents().stream()
        .map(FunctionChallengeGpt.studentSummaryFunction)
        .forEach(log::info);

    log.info("\n--- Normalized Names ---");
    StudentDatabase.getAllStudents().stream()
        .map(FunctionChallengeGpt.normalizeName)
        .map(FunctionChallengeGpt.fullNameFunction)
        .forEach(log::info);

    log.info("\n--- After GPA Boost ---");
    StudentDatabase.getAllStudents().stream()
        .map(FunctionChallengeGpt.gpaBoost)
        .map(FunctionChallengeGpt.studentSummaryFunction)
        .forEach(log::info);

    log.info("\n--- After Fixing Probation ---");
    StudentDatabase.getAllStudents().stream()
        .map(FunctionChallengeGpt.probationFixer)
        .map(FunctionChallengeGpt.studentSummaryFunction)
        .forEach(log::info);

    log.info("\n--- Student with Highest GPA ---");
    StudentDatabase.getAllStudents().stream()
        .reduce(FunctionChallengeGpt.higherGpa)
        .ifPresent(s -> log.info(FunctionChallengeGpt.studentSummaryFunction.apply(s)));

    log.info("\n--- Merged First Two Students ---");
    var students = StudentDatabase.getAllStudents();
    if (students.size() >= 2) {
      Student merged = FunctionChallengeGpt.mergeHobbies.apply(students.get(0), students.get(1));
      log.info(FunctionChallengeGpt.studentSummaryFunction.apply(merged));
    }

    log.info("\n--- Best Student (using BinaryOperator) ---");
    StudentDatabase.getAllStudents().stream()
        .reduce(FunctionChallengeGpt.bestStudent)
        .ifPresent(s -> log.info(FunctionChallengeGpt.studentSummaryFunction.apply(s)));

  }
}