package com.main.section.section6;

import com.main.lambdaintros.RunnableExample;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SectionSix {

  public static void lambdaExamples(){
    log.info("Simple examples of lambda expressions");

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