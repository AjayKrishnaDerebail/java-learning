package com.main.utility;

import com.main.model.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentDatabase {
    
    private static final List<Student> students = createSampleStudents();
    
    private static List<Student> createSampleStudents() {
        return Arrays.asList(
            new Student("John", "Doe", 3.8, "A", Gender.M, Arrays.asList("Reading", "Swimming", "Chess", "Dancing"), 12),
            new Student("Alice", "Smith", 2.9, "B", Gender.F, Arrays.asList("Painting", "Dancing"), 13),
            new Student("Bob", "Johnson", 2.5, "C", Gender.M, Arrays.asList("Coding", "Gaming", "Hiking"), 9),
            new Student("Emma", "Williams", 3.7, "A-", Gender.F, Arrays.asList("Singing", "Photography"), 8),
            new Student("Michael", "Brown", 4.2, "A+", Gender.M, Arrays.asList("Basketball", "Movies", "Cooking"), 10),
            new Student("Sarah", "Davis", 4.0, "A", Gender.F, Arrays.asList("Debate", "Journalism"), 15)
        );
    }
    
    /**
     * Returns an unmodifiable list of all students in the database
     * @return List of all students
     */
    public static List<Student> getAllStudents() {
        return List.copyOf(students);
    }

  /**
   * Simulates searching for a student.
   * Returns Optional because the student might not exist!
   */
  public static Optional<Student> findByName(String firstName) {
    log.info(">>> [DB] Scanning tables for firstName : {}" , firstName);
    return students.stream()
        .filter(s -> s.firstName().equals(firstName))
        .findFirst();
  }
}
