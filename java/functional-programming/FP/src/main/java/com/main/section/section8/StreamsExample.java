package com.main.section.section8;

import com.main.database.StudentDatabase;
import com.main.model.Student;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamsExample {
  public static void simpleStreamExample(){
    var studentHobbiesMap = StudentDatabase.getAllStudents().parallelStream()
        //.peek(System.out::println)
        .filter(s -> s.gpa() >= 4.0)
        .peek(System.out::println)
        .collect(Collectors.toMap(Student::firstName, Student::hobbies));

    //log.info("{}",studentHobbiesMap);
  }
}