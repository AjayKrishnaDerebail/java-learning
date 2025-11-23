package com.main;

import com.main.comparision.ImperativeVsDeclarative;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Hello world!
 *
 */

@Slf4j
public class App {

  public static void main(String[] args) {
    val declarativeStyleList = ImperativeVsDeclarative.getNonDuplicateListDeclarativeStyle();
    val imperativeStyleList = ImperativeVsDeclarative.getNonDuplicateListImperativeStyle();

    log.info("{}" , declarativeStyleList);
    log.info("{}" , imperativeStyleList);
  }
}
