package com.learnjava.parallelstream;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.util.DataSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.val;

@NoArgsConstructor
public class ParallelStreamExample {

  void main() {
    val namesList = DataSet.namesList();
    startTimer();
    val resultList = stringTransform(namesList,true);
    log("ResultList : " + resultList);
    timeTaken();
  }

  public List<String> stringTransform(final List<String> namesList,final boolean isParallel) {
    val namesStream = isParallel ? namesList.parallelStream() : namesList.stream();
    return namesStream
        .map(this::addNameLengthTransform)
        .collect(Collectors.toList());
  }

  private String addNameLengthTransform(final String name) {
    delay(500);
    return name.length() + " - " + name;
  }

}