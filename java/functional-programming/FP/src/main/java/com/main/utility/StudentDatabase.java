package com.main.utility;

import com.main.model.Student;
import java.util.Arrays;
import java.util.List;

public class StudentDatabase {
    
    private static final List<Student> students = createSampleStudents();
    
    private static List<Student> createSampleStudents() {
        return Arrays.asList(
            new Student("John", "Doe", 3.8, Arrays.asList("Reading", "Swimming", "Chess","Dancing")),
            new Student("Alice", "Smith", 2.9, Arrays.asList("Painting", "Dancing")),
            new Student("Bob", "Johnson", 2.5, Arrays.asList("Coding", "Gaming", "Hiking")),
            new Student("Emma", "Williams", 3.7, Arrays.asList("Singing", "Photography")),
            new Student("Michael", "Brown", 4.2, Arrays.asList("Basketball", "Movies", "Cooking")),
            new Student("Sarah", "Davis", 4.0, Arrays.asList("Debate", "Journalism"))
        );
    }
    
    /**
     * Returns an unmodifiable list of all students in the database
     * @return List of all students
     */
    public static List<Student> getAllStudents() {
        return List.copyOf(students);
    }
}
