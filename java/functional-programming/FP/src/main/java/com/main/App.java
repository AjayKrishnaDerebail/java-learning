package com.main;

import com.main.comparision.ImperativeVsDeclarative;
import com.main.database.StudentDatabase;
import com.main.lambdaintros.RunnableExample;
import com.main.model.Student;
import java.util.Comparator;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Hello world!
 *
 */

@Slf4j
public class App {

  public static void main(String[] args) {

    /* Section 5 */

    log.info("Difference between imperative and declarative style of programming");

    {
      val declarativeStyleList = ImperativeVsDeclarative.getNonDuplicateListDeclarativeStyle();
      val imperativeStyleList = ImperativeVsDeclarative.getNonDuplicateListImperativeStyle();

      log.info("{}", declarativeStyleList);
      log.info("{}", imperativeStyleList);
    }

    /* Section 6 */

    log.info("Simple examples of lambda expressions");

    {

      //Runnable

      Thread t1 = new Thread(new RunnableExample());
      t1.start();

      final Runnable runnableLambda = () -> log.info("Inside runnable lambda");

      new Thread(runnableLambda).start();

      //Comparator

      Comparator<Integer> integerComparator = (x, y) -> x.compareTo(y); //Integer::compareTo

      log.info("{}", integerComparator.compare(5, 10));

      log.info("{}", integerComparator.compare(10, 5));

      Comparator<Integer> integerComparatorWithNaturalOrderMethodReference = Comparator.naturalOrder();

      log.info("{}", integerComparatorWithNaturalOrderMethodReference.compare(5, 5));
    }

    /* Section 7 */

    log.info("Simple examples of consumer");

    {

      Consumer<String> upperCaseConsumer = (string) -> log.info(string.toUpperCase());

      upperCaseConsumer.accept("hello");

    }

    {
      Consumer<Student> firstConsumer = (student) -> log.info(student.toString());
      Consumer<Student> secondConsumer = (student) -> log.info("{} gpa is {}", student.firstName(),
          student.gpa());
      val studentList = StudentDatabase.getAllStudents();

      studentList.forEach(student -> {
        if (student.gpa() > 3) {
          secondConsumer.andThen(firstConsumer).accept(student);
        }
      });
    }
  }

}