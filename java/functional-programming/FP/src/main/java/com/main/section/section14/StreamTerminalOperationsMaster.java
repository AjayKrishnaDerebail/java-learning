package com.main.section.section14;

import com.main.model.Student;
import com.main.utility.Gender;
import com.main.utility.StudentDatabase;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamTerminalOperationsMaster {
  private static final String LINE_SEPARATOR = "-------------------------------------\n";

  public static void callTerminalExamples() {

    log.info("Terminal Operations Master class");

    List<Student> students = StudentDatabase.getAllStudents();

    runJoiningExamples(students);
    log.info(LINE_SEPARATOR);
    runCountingExamples(students);
    log.info(LINE_SEPARATOR);
    runMappingExamples(students);
    log.info(LINE_SEPARATOR);
    runMinMaxExamples(students);
    log.info(LINE_SEPARATOR);
    runArithmeticExamples(students);
    log.info(LINE_SEPARATOR);
    runGroupingExamples(students);
    log.info(LINE_SEPARATOR);
    runPartitioningExamples(students);
    log.info(LINE_SEPARATOR);
    runCollectionExamples(students);
    log.info(LINE_SEPARATOR);
  }

  /**
   * Joining: Combining stream elements into a String.
   */
  private static void runJoiningExamples(List<Student> students) {
    log.info("--- JOINING EXAMPLES ---");

    // Easy: Simple concat
    String basicJoin = students.stream()
        .map(Student::firstName)
        .collect(Collectors.joining());
    log.info("Joining 1 (Basic): {}", basicJoin);

    // Medium: With Delimiter
    String delimiterJoin = students.stream()
        .map(Student::firstName)
        .collect(Collectors.joining("-"));
    log.info("Joining 2 (Delimiter): {}", delimiterJoin);

    // Hard: Full Prefix/Suffix
    String fullJoin = students.stream()
        .map(Student::firstName)
        .collect(Collectors.joining(", ", "STUDENTS[", "]"));
    log.info("Joining 3 (Full): {}", fullJoin);
  }

  /**
   * Counting: Determining the size of the stream.
   */
  private static void runCountingExamples(List<Student> students) {
    log.info("--- COUNTING EXAMPLES ---");

    // Easy: Total count
    long totalCount = students.stream().collect(Collectors.counting());
    log.info("Count 1 (Total): {}", totalCount);

    // Medium: Filtered count
    long highGpaCount = students.stream()
        .filter(s -> s.gpa() > 3.8)
        .collect(Collectors.counting());
    log.info("Count 2 (GPA > 3.8): {}", highGpaCount);

    // Hard: Count unique grades (nested)
    long uniqueGradesCount = students.stream()
        .map(Student::grade)
        .distinct()
        .collect(Collectors.counting());
    log.info("Count 3 (Unique Grades): {}", uniqueGradesCount);
  }

  /**
   * Mapping: Transforming elements during the collection phase.
   */
  private static void runMappingExamples(List<Student> students) {
    log.info("--- MAPPING EXAMPLES ---");

    // Easy: Simple transformation to List
    List<String> firstNameList = students.stream()
        .collect(Collectors.mapping(Student::firstName, Collectors.toList()));
    log.info("Mapping 1 (List): {}", firstNameList);

    // Medium: Transformation to Set (Unique hobbies)
    Set<String> uniqueHobbies = students.stream()
        .flatMap(s -> s.hobbies().stream())
        .collect(Collectors.mapping(String::toUpperCase, Collectors.toSet()));
    log.info("Mapping 2 (Set): {}", uniqueHobbies);

    // Hard: Mapping within a grouping
    Map<Gender, List<String>> lastNameByGender = students.stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.mapping(Student::lastName, Collectors.toList())
        ));
    log.info("Mapping 3 (Inside Group): {}", lastNameByGender);
  }

  /**
   * MinBy / MaxBy: Finding extreme values using Comparators.
   */
  private static void runMinMaxExamples(List<Student> students) {
    log.info("--- MIN/MAX EXAMPLES ---");

    // Easy: Highest GPA
    Optional<Student> maxGpaStudent = students.stream()
        .collect(Collectors.maxBy(Comparator.comparing(Student::gpa)));
    maxGpaStudent.ifPresent(s -> log.info("MaxBy (GPA): {}", s));

    // Medium: Lowest Notebook count
    Optional<Student> minNotebookStudent = students.stream()
        .collect(Collectors.minBy(Comparator.comparing(Student::noteBooks)));
    minNotebookStudent.ifPresent(s -> log.info("MinBy (Books): {}", s));

    // Hard: Using collectingAndThen to remove Optional
    Student top = students.stream()
        .collect(Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparing(Student::gpa)),
            Optional::get
        ));
    log.info("MaxBy (Unwrapped): {}", top.firstName());
  }

  /**
   * Summing / Averaging / Summarizing.
   */
  private static void runArithmeticExamples(List<Student> students) {
    log.info("--- ARITHMETIC EXAMPLES ---");

    // Easy: Total notebooks
    int totalNotebooks = students.stream()
        .collect(Collectors.summingInt(Student::noteBooks));
    log.info("Summing: {}", totalNotebooks);

    // Medium: Average GPA
    double averageGpa = students.stream()
        .collect(Collectors.averagingDouble(Student::gpa));
    log.info("Averaging: {}", averageGpa);

    // Hard: All-in-one Statistics
    DoubleSummaryStatistics stats = students.stream()
        .collect(Collectors.summarizingDouble(Student::gpa));
    log.info("Summarizing: Max: {}, Avg: {}, Count: {}",
        stats.getMax(),
        stats.getAverage(),
        stats.getCount());
  }

  /**
   * GroupingBy: The 3 flavors + Multi-level + MaxBy integration.
   */
  private static void runGroupingExamples(List<Student> students) {
    log.info("--- GROUPING EXAMPLES ---");

    // 1. Simple Grouping
    Map<Gender, List<Student>> groupByGender = students.stream()
        .collect(Collectors.groupingBy(Student::gender));
    log.info("Grouping (Simple): {}", groupByGender);

    // 2. Downstream Grouping (Gender to Count)
    Map<Gender, Long> countByGender = students.stream()
        .collect(Collectors.groupingBy(Student::gender, Collectors.counting()));
    log.info("Grouping (Downstream): {}", countByGender);

    // 3. Customized Grouping (3 Arguments: Key, Supplier, Downstream)
    TreeMap<String, List<Student>> sortedGrades = students.stream()
        .collect(Collectors.groupingBy(
            Student::grade,
            TreeMap::new,
            Collectors.toList()
        ));
    log.info("Grouping (Custom Supplier - TreeMap): {}", sortedGrades);

    // 4. Multi-level Grouping (Gender -> Grade -> List)
    Map<Gender, Map<String, List<Student>>> multiLevelGrouping = students.stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.groupingBy(Student::grade)
        ));
    log.info("Grouping (Multi-level): {}", multiLevelGrouping);

    // 5. Grouping + MaxBy + collectingAndThen (Top student per gender)
    Map<Gender, Student> topByGender = students.stream()
        .collect(Collectors.groupingBy(
            Student::gender,
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparing(Student::gpa)),
                Optional::get
            )
        ));
    log.info("Grouping (Top GPA per Gender): {}", topByGender);
  }

  /**
   * PartitioningBy: Special binary grouping.
   */
  private static void runPartitioningExamples(List<Student> students) {
    log.info("--- PARTITIONING EXAMPLES ---");

    // Easy: Simple Predicate
    Map<Boolean, List<Student>> gpaPartition = students.stream()
        .collect(Collectors.partitioningBy(s -> s.gpa() > 3.5));
    log.info("Partition (GPA > 3.5): {}", gpaPartition);

    // Medium: Partition + Mapping (Just names)
    Map<Boolean, Set<String>> namePartition = students.stream()
        .collect(Collectors.partitioningBy(
            s -> s.gpa() > 3.5,
            Collectors.mapping(Student::firstName, Collectors.toSet())
        ));
    log.info("Partition (Mapping): {}", namePartition);

    // Hard: Partition + Nested grouping
    Map<Boolean, Map<Gender, List<Student>>> nestedPartition = students.stream()
        .collect(Collectors.partitioningBy(
            s -> s.gpa() > 3.5,
            Collectors.groupingBy(Student::gender)
        ));
    log.info("Partition (Nested): {}", nestedPartition);
  }

  /**
   * toMap: Converting to custom key-value pairs.
   */
  private static void runCollectionExamples(List<Student> students) {
    log.info("--- COLLECTION (toMAP) EXAMPLES ---");

    // Easy: Simple Name to GPA
    Map<String, Double> nameToGpa = students.stream()
        .collect(Collectors.toMap(Student::firstName, Student::gpa));
    log.info("ToMap 1 (Basic): {}", nameToGpa);

    // Medium: Resolving duplicate keys (BinaryOperator)
    // If two students have the same first name, keep the one with higher GPA
    Map<String, Student> bestStudentByName = students.stream()
        .collect(Collectors.toMap(
            Student::firstName,
            Function.identity(),
            (s1, s2) -> s1.gpa() > s2.gpa() ? s1 : s2
        ));
    log.info("ToMap 2 (Collision Handling): {}", bestStudentByName);

    // Hard: Specific Map implementation (LinkedHashMap to preserve order)
    Map<String, Double> orderedMap = students.stream()
        .collect(Collectors.toMap(
            Student::firstName,             // 1. Key
            Student::gpa,                   // 2. Value
            (existing, replacement) -> existing, // 3. Merge Function (Keep old if name duplicates)
            LinkedHashMap::new              // 4. Map Supplier (The "Recipe")
        ));
    log.info("ToMap 3 (Custom Implementation): {}", orderedMap);
  }
}