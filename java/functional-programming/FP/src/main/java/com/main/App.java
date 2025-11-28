package com.main;

import com.main.section.section5.SectionFive;
import com.main.section.section7.ConsumerExamples;
import com.main.section.section6.SectionSix;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    /* Section 5 */

    SectionFive.imperativeVsDeclarativeStyle();

    /* Section 6 */

    SectionSix.lambdaExamples();

    /* Section 7 */

    ConsumerExamples.consumerExamples();


  }

}