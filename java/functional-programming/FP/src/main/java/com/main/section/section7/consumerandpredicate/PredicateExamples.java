package com.main.section.section7.consumerandpredicate;

import com.main.utility.StudentDatabase;
import com.main.model.Student;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PredicateExamples {

  public static Predicate<Student> p1 = s -> s.gpa() >= 3.0;

  static Predicate<Student> p2 = s -> s.gpa() < 4.0;

  public static void filterStudentByGrade(){
    List<Student> studentList = StudentDatabase.getAllStudents();

    studentList.forEach(s ->{
      if(p1.and(p2).test(s)){
        log.info("{}",s);
      }
    });
  }

}