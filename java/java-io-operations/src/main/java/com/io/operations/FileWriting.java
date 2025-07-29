package com.io.operations;

import com.io.model.Student;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriting {

  public static void main(String[] args) {
    List<Student> students = createSampleStudents();
    String filePath = "files/students_data.csv";

    // Write using FileWriter
    //writeWithFileWriter(filePath, students);

    // Write using BufferedWriter
    Path bufferedFilePath = Path.of("files/students_data_buffered.csv");
    //writeWithBufferedWriter(bufferedFilePath, students);

    //renameFile();

    createDirectoryAndMoveFile();
  }

  private static void createDirectoryAndMoveFile() {
    Path oldPath = Path.of("test.csv");
    Path newPath = Path.of("newFilesPath/test.csv");
    try {
      Files.createDirectories(newPath.getParent());
      Files.move(oldPath, newPath);
    } catch (IOException e) {
      log.error("Error moving file", e);
    }
  }

  private static void renameFile() {
    boolean renamed = false;
    File oldFile = new File("files/test2.csv");
    File newFile = new File("files/test3.csv");
    if (oldFile.exists()) {
      renamed = oldFile.renameTo(newFile);
    }
    log.info(renamed ? "File renamed" : "File not renamed");

    // This is not preferred as java.io classes dont throw exceptions

    Path oldFilePath = Path.of("files/test3.csv");
    Path newFilePath = Path.of("files/test4.csv");
    if (Files.exists(oldFilePath)) {
      try {
        Files.move(oldFilePath, newFilePath);
        log.info("File renamed using java.nio classes");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

  /**
   * Writes student data to a file using FileWriter
   *
   * @param filePath Path to the output file
   * @param students List of Student objects to write
   */
  public static void writeWithFileWriter(String filePath, List<Student> students) {
    // Using try-with-resources to ensure the writer is closed automatically
    try (FileWriter writer = new FileWriter(filePath)) {
      // Write header
      writer.write("Student ID,Name,Email,Phone\n");

      // Write each student's data
      for (Student student : students) {
        String line = String.format("%d,%s,%s,%s%n", student.getStudentId(), student.getName(),
            student.getEmail(), student.getPhone());
        writer.write(line);
      }

      System.out.println("Data successfully written using FileWriter to: " + filePath);
    } catch (IOException e) {
      System.err.println("Error writing to file with FileWriter: " + e.getMessage());
    }
  }

  /**
   * Writes student data to a file using BufferedWriter
   *
   * @param filePath Path to the output file
   * @param students List of Student objects to write
   */
  public static void writeWithBufferedWriter(Path filePath, List<Student> students) {
    // Using try-with-resources to ensure the writer is closed automatically
    try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
      // Write header
      writer.write("Student ID,Name,Email,Phone\n");

      // Write each student's data
      for (Student student : students) {
        String line = String.format("%d,%s,%s,%s", student.getStudentId(), student.getName(),
            student.getEmail(), student.getPhone());
        writer.newLine();
        writer.write(line);
      }

      System.out.println("Data successfully written using BufferedWriter to: " + filePath);
    } catch (IOException e) {
      System.err.println("Error writing to file with BufferedWriter: " + e.getMessage());
    }
  }

  /**
   * Helper method to create sample student data
   *
   * @return List of sample Student objects
   */
  private static List<Student> createSampleStudents() {
    List<Student> students = new ArrayList<>();

    // Add sample students
    students.add(new Student(2001, "Alex Johnson", "alex.j@email.com", "555-0101"));
    students.add(new Student(2002, "Sarah Williams", "sarah.w@email.com", "555-0102"));
    students.add(new Student(2003, "James Wilson", "james.w@email.com", "555-0103"));
    students.add(new Student(2004, "Emma Brown", "emma.b@email.com", "555-0104"));
    students.add(new Student(2005, "Daniel Miller", "daniel.m@email.com", "555-0105"));

    return students;
  }
}
