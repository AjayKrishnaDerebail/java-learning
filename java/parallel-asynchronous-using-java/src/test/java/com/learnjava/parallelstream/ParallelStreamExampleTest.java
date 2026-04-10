package com.learnjava.parallelstream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.learnjava.util.DataSet;
import lombok.val;
import org.junit.jupiter.api.Test;

public class ParallelStreamExampleTest {

  final ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

  @Test
  void testStreamExample_testSizeAndTransformOperation() {

    val resultList = parallelStreamExample.stringTransform(DataSet.namesList());

    assertEquals(4, resultList.size());

    resultList
        .forEach(name -> assertTrue(name.contains("-"))
        );
  }

}