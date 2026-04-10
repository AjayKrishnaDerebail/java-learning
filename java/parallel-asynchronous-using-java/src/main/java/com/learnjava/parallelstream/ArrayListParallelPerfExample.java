package com.learnjava.parallelstream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListParallelPerfExample {

  public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, final boolean isParallel) {
    startTimer();
    Stream<Integer> integerStream = inputList.stream();
    integerStream = isParallel ? integerStream.parallel() : integerStream;

    List<Integer> resultList =
        integerStream
            .map(num -> num * 2)
            .toList();

    timeTaken();

    return resultList;
  }
}
