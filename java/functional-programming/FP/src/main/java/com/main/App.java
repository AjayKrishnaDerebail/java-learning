package com.main;

import com.main.section.section5.SectionFive;
import com.main.section.section7.ConsumerExamples;
import com.main.section.section6.SectionSix;
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

    ConsumerExamples.consumerExamples();

    log.info("Predicate examples");

    PredicateExamples.filterStudentByGrade();
  }

}