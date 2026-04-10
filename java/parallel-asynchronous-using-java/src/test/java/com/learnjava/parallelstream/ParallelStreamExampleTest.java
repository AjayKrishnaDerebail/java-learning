package com.learnjava.parallelstream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.learnjava.util.DataSet;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParallelStreamExampleTest {

  final ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void testStreamExample_testSizeAndTransformOperation(final boolean isParallel) {

    val resultList = parallelStreamExample.stringTransform(DataSet.namesList(), isParallel);

    assertEquals(4, resultList.size());

    resultList.forEach(name -> assertTrue(name.contains("-")));
  }

}