package com.learnjava.parallelstream;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.util.DataSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;

public class ParallelStreamExample {

  static void main() {
    val namesList = DataSet.namesList();
    startTimer();
    val resultList = stringTransform(namesList);
    log("ResultList : " + resultList);
    timeTaken();
  }

  private static List<String> stringTransform(final List<String> namesList) {
    return namesList.parallelStream()
        .map(ParallelStreamExample::addNameLengthTransform)
        .collect(Collectors.toList());
  }

  private static String addNameLengthTransform(final String name) {
    delay(500);
    return name.length() + " - " + name;
  }

}