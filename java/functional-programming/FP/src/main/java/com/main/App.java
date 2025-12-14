package com.main;

import static com.main.section.section7.PredicateExamples.p1;

import com.main.database.StudentDatabase;
import com.main.section.section5.SectionFive;
import com.main.section.section7.ConsumerAndPredicate;
import com.main.section.section7.ConsumerAndPredicateChallenge;
import com.main.section.section7.ConsumerExamples;
import com.main.section.section6.SectionSix;
import com.main.section.section7.FunctionExample;
import com.main.section.section7.PredicateExamples;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  static void main() {

    /* Section 5 */

    SectionFive.imperativeVsDeclarativeStyle();

    /* Section 6 */

    SectionSix.lambdaExamples();

    /* Section 7 */

    {

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

      log.info("{}",FunctionExample.biFunction.apply(StudentDatabase.getAllStudents(), p1));
    }


  }

}