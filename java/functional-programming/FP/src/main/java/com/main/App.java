package com.main;

import com.main.section.SectionFive;
import com.main.section.SectionSeven;
import com.main.section.SectionSix;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    /* Section 5 */

    SectionFive.imperativeVsDeclarativeStyle();

    /* Section 6 */
    SectionSix.lambdaExamples();

    /* Section 7 */

    SectionSeven.consumerExamples();
  }

}