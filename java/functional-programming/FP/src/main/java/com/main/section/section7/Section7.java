package com.main.section.section7;

import static com.main.section.section7.PredicateExamples.p1;

import com.main.database.StudentDatabase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Section7 {

  public static void javaUtilFunctionPackageExamples() {
    ConsumerExamples.consumerExamples();

    log.info("Predicate examples");
    PredicateExamples.filterStudentByGrade();

    log.info("Predicate and Consumer examples");
    ConsumerAndPredicate.printName();

    log.info("Predicate and Consumer examples challenge by ChatGPT");
    ConsumerAndPredicateChallenge.purePredicateChallenge();
    ConsumerAndPredicateChallenge.purePredicateChallengeUsingStream();

    log.info("Challenge combination epic");
    ConsumerAndPredicateChallenge.ConsumerAndPredicateChatGptChallenge();
    log.info("Challenge combination epic rule based solution");
    ConsumerAndPredicateChallenge.ruleBasedExecution();

    log.info("BiFunction example");
    log.info("{}", FunctionExample.biFunction.apply(StudentDatabase.getAllStudents(), p1));
  }

}