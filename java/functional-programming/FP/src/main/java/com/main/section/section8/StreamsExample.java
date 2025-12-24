package com.main.section.section8;

import com.main.utility.StudentDatabase;
import com.main.model.Student;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

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
}