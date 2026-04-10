package com.learnjava.parallelstream;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learnjava.util.DataSet;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

@Slf4j
public class ArrayListParallelPerfExampleTest {

  private final ArrayListParallelPerfExample integerArrayList = new ArrayListParallelPerfExample();

  @BeforeEach
  void setUp() {
    stopWatchReset();
  }


  @RepeatedTest(5)
  void test_sequential() {
    int size = 100_000;
    val inputList = DataSet.generateArrayList(size);

    log.info("Sequential stream performance");

    val resultList = integerArrayList.multiplyEachValue(inputList, false);

    assertEquals(size, resultList.size());
  }

  @RepeatedTest(5)
  void test_parallel() {
    int size = 100_000;
    val inputList = DataSet.generateArrayList(size);

    log.info("Parallel stream performance");

    val resultList = integerArrayList.multiplyEachValue(inputList, true);

    assertEquals(size, resultList.size());
  }

}