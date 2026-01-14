package com.main.section.section14;

import com.main.model.Student;
import com.main.utility.Gender;
import com.main.utility.StudentDatabase;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamTerminalOperationsExample {

  private static final String LINE_SEPARATOR = "-------------------------------------\n";

  // 1. Joining
  private static void joiningExample() {
    String result = StudentDatabase.getAllStudents().stream()
        .map(Student::firstName)
        .collect(Collectors.joining(", ", "[", "]"));
    log.info("1. Joining Example: {}", result);
  }

  // 2. Counting
  private static void countingExample() {
    long count = StudentDatabase.getAllStudents().stream()
        .filter(student -> student.gpa() >= 3.5)
        .count();
    log.info("2. Counting Example (GPA >= 3.5): {}", count);
  }

  // 3. Mapping
  private static void mappingExample() {
    List<String> names = StudentDatabase.getAllStudents().stream()
        .map(student -> student.firstName() + " " + student.lastName())
        .collect(Collectors.toList());
    log.info("3. Mapping Example: {}", names);
  }

  // 4. minBy
  private static void minByExample() {
    Student student = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.minBy(Comparator.comparing(Student::gpa)))
        .orElse(null);
    log.info("4. Student with min GPA: {}", student);
  }

  // 5. maxBy
  private static void maxByExample() {
    Student student = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.maxBy(Comparator.comparing(Student::gpa)))
        .orElse(null);
    log.info("5. Student with max GPA: {}", student);
  }

  // 6. summingInt, summingDouble
  private static void sumExample() {
    double sum = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.summingDouble(Student::gpa));
    log.info("6. Sum of all GPAs: {}", sum);
  }

  // 7. averagingDouble
  private static void averageExample() {
    double average = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.averagingDouble(Student::gpa));
    log.info("7. Average GPA: {}", average);
  }

  // 8. groupingBy - Simple
  private static void groupingByGender() {
    Map<Gender, List<Student>> studentsByGender = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(Student::gender));
    log.info("8. Grouping by gender: {}", studentsByGender);
  }

  // 9. groupingBy with Downstream - Count students by gender
  private static void groupingByGenderWithCount() {
    Map<Gender, Long> genderCount = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.counting()
        ));
    log.info("9. Count of students by gender: {}", genderCount);
  }

  // 10. groupingBy with mapping - Get list of names by gender
  private static void groupingByGenderWithMapping() {
    Map<Gender, List<String>> namesByGender = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.mapping(Student::firstName, Collectors.toList())
        ));
    log.info("10. Student names by gender: {}", namesByGender);
  }

  // 11. Custom grouping - Group by grade
  private static void customGroupingByGrade() {
    Map<String, List<Student>> studentsByGrade = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(Student::grade));
    log.info("11. Students grouped by grade: {}", studentsByGrade);
  }

  // 12. Multi-level grouping - Group by gender and then by grade
  private static void multiLevelGrouping() {
    Map<Gender, Map<String, List<Student>>> multiLevelMap = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.groupingBy(Student::grade)
        ));
    log.info("12. Multi-level grouping (gender then grade): {}", multiLevelMap);
  }

  // 13. groupingBy with maxBy - Get student with highest GPA by gender
  private static void groupingByWithMaxBy() {
    Map<Gender, Optional<Student>> topStudentsByGender = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.maxBy(Comparator.comparing(Student::gpa))
        ));
    log.info("13. Top student by gender (with Optional): {}", topStudentsByGender);
  }

  // 14. groupingBy with collectingAndThen - Get student with highest GPA by gender (without Optional)
  private static void groupingByWithCollectingAndThen() {
    Map<Gender, Student> topStudents = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparing(Student::gpa)),
                opt -> opt.orElse(null)
            )
        ));
    log.info("14. Top student by gender: {}", topStudents);
  }

  // 15. partitioningBy - Split students into two groups based on GPA
  private static void partitioningByGPA() {
    Map<Boolean, List<Student>> partitionedStudents = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.partitioningBy(student -> student.gpa() >= 3.0));
    log.info("15. Students partitioned by GPA (>= 3.0): {}", partitionedStudents);
  }

  // 16. partitioningBy with downstream - Count students in each partition
  private static void partitioningByWithCounting() {
    Map<Boolean, Long> partitionCounts = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.partitioningBy(
            student -> student.gpa() >= 3.0,
            Collectors.counting()
        ));
    log.info("16. Count of students by GPA partition (>= 3.0): {}", partitionCounts);
  }

  // 17. Collecting to Set
  private static void collectToSet() {
    Set<String> uniqueGrades = StudentDatabase.getAllStudents().stream()
        .map(Student::grade)
        .collect(Collectors.toSet());
    log.info("17. Unique grades: {}", uniqueGrades);
  }

  // 18. Collecting to Map
  private static void collectToMap() {
    Map<String, Double> studentGpaMap = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.toMap(
            student -> student.firstName() + " " + student.lastName(),
            Student::gpa
        ));
    log.info("18. Student name to GPA map: {}", studentGpaMap);
  }

  // 19. Collecting with reducing
  private static void reducingExample() {
    BinaryOperator<Student> higherGpa = (s1, s2) -> s1.gpa() >= s2.gpa() ? s1 : s2;
    Optional<Student> topStudent = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.reducing(higherGpa));
    log.info("19. Top student using reducing: {}", topStudent.orElse(null));
  }

  // 20. Collecting with summarizing
  private static void summarizingExample() {
    DoubleSummaryStatistics stats = StudentDatabase.getAllStudents().stream()
        .collect(Collectors.summarizingDouble(Student::gpa));
    log.info("20. GPA Statistics - Count: {}, Sum: {}, Min: {}, Average: {}, Max: {}",
        stats.getCount(), stats.getSum(), stats.getMin(), stats.getAverage(), stats.getMax());
  }

  public static void callAllMethods() {
    log.info("Stream Terminal Operations Example Class");
    joiningExample();
    log.info(LINE_SEPARATOR);
    countingExample();
    log.info(LINE_SEPARATOR);
    mappingExample();
    log.info(LINE_SEPARATOR);
    minByExample();
    log.info(LINE_SEPARATOR);
    maxByExample();
    log.info(LINE_SEPARATOR);
    sumExample();
    log.info(LINE_SEPARATOR);
    averageExample();
    log.info(LINE_SEPARATOR);
    groupingByGender();
    log.info(LINE_SEPARATOR);
    groupingByGenderWithCount();
    log.info(LINE_SEPARATOR);
    groupingByGenderWithMapping();
    log.info(LINE_SEPARATOR);
    customGroupingByGrade();
    log.info(LINE_SEPARATOR);
    multiLevelGrouping();
    log.info(LINE_SEPARATOR);
    groupingByWithMaxBy();
    log.info(LINE_SEPARATOR);
    groupingByWithCollectingAndThen();
    log.info(LINE_SEPARATOR);
    partitioningByGPA();
    log.info(LINE_SEPARATOR);
    partitioningByWithCounting();
    log.info(LINE_SEPARATOR);
    collectToSet();
    log.info(LINE_SEPARATOR);
    collectToMap();
    log.info(LINE_SEPARATOR);
    reducingExample();
    log.info(LINE_SEPARATOR);
    summarizingExample();
  }

}