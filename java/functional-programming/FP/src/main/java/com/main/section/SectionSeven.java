package com.main.section;

import com.main.database.StudentDatabase;
import com.main.model.Student;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class SectionSeven {

  public static void consumerExamples(){

    log.info("Simple examples of consumer");

    Consumer<String> upperCaseConsumer = (string) -> log.info(string.toUpperCase());
    upperCaseConsumer.accept("hello");

    Consumer<Student> firstConsumer = (student) -> log.info(student.toString());
    Consumer<Student> secondConsumer = (student) -> log.info("{} gpa is {}", student.firstName(),
        student.gpa());
    val studentList = StudentDatabase.getAllStudents();
    studentList.forEach(student -> {
      if (student.gpa() > 3) {
        secondConsumer.andThen(firstConsumer).accept(student);
      }
    });

    log.info("Challenge portion \n");

    Consumer<Student> printFullNameConsumer = (student) -> log.info(
        student.firstName() + " " + student.lastName());
    Consumer<Student> gradeStudent = (student) ->{
      if(student.gpa() >= 4)
        log.info("Honor student : {}",student.firstName());
      else if(student.gpa() >= 3)
        log.info("Good student : {}",student.firstName());
      else
        log.info("Academic Probation : {}",student.firstName());
    };
    Consumer<Student> printPrimaryHobbyConsumer = (student) -> {
      if(!student.hobbies().isEmpty())
        log.info("{}'s primary hobby is {}",student.firstName(),student.hobbies().getFirst());
    };
    Consumer<Student> warnStudentHobbiesTooMuchConsumer = (student) -> {
      if (student.hobbies().size() >= 3 ){
        log.info("{} , you have too many hobbies",student.firstName());
      }
    };
    Consumer<Student> lineSeparator = (s) -> log.info("------------------------");

    Consumer<Student> chain = printFullNameConsumer
        .andThen(gradeStudent)
        .andThen(printPrimaryHobbyConsumer)
        .andThen(warnStudentHobbiesTooMuchConsumer)
        .andThen(lineSeparator);

    studentList.forEach(chain);
  }

}