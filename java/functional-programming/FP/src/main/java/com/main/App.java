package com.main;

import com.main.comparision.ImperativeVsDeclarative;
import com.main.lambdaintros.RunnableExample;
import java.util.Comparator;
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

    {
      val declarativeStyleList = ImperativeVsDeclarative.getNonDuplicateListDeclarativeStyle();
      val imperativeStyleList = ImperativeVsDeclarative.getNonDuplicateListImperativeStyle();

      log.info("{}", declarativeStyleList);
      log.info("{}", imperativeStyleList);
    }

    /* Section 6 */

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
  }

}