package com.main.section.section7;

import static com.main.section.section7.consumerandpredicate.PredicateExamples.p1;

import com.main.utility.StudentDatabase;
import com.main.section.section7.consumerandpredicate.ConsumerAndPredicate;
import com.main.section.section7.consumerandpredicate.ConsumerAndPredicateChallenge;
import com.main.section.section7.consumerandpredicate.ConsumerExamples;
import com.main.section.section7.consumerandpredicate.PredicateExamples;
import com.main.section.section7.function.FunctionChallengeGpt;
import com.main.section.section7.function.FunctionExample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
public class SectionSeven {

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

    log.info("Pure BiFunction example");
    log.info("{}", FunctionExample.biFunctionPurelyFunctional.apply(StudentDatabase.getAllStudents(), p1));
    
    log.info("\n===== Function Challenge Demo =====");
    FunctionChallengeGpt.functionChallengeDemo();
  }

}