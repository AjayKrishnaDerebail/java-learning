package com.main.section.section5;

import com.main.comparision.ImperativeVsDeclarative;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class SectionFive {

  public static void imperativeVsDeclarativeStyle(){
    log.info("Difference between imperative and declarative style of programming");

    val declarativeStyleList = ImperativeVsDeclarative.getNonDuplicateListDeclarativeStyle();
    val imperativeStyleList = ImperativeVsDeclarative.getNonDuplicateListImperativeStyle();

    log.info("{}", declarativeStyleList);
    log.info("{}", imperativeStyleList);
  }

}