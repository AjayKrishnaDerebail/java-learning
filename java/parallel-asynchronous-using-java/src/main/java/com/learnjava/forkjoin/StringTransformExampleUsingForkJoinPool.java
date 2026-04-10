package com.learnjava.forkjoin;

import static com.learnjava.util.CommonUtil.stopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class StringTransformExampleUsingForkJoinPool {

  static void main() {
    val names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

    stopWatch.start();
    try (val pool = new ForkJoinPool()) {
      val task = new TransformTask(names);
      val result = pool.invoke(task);
      log.info("Final Result: {}" , result);
    }

    stopWatch.stop();
    log.info("Total Time Taken : {}ms " , stopWatch.getTime());
  }

  static String transform(String name) {
    // Actual work happens here
    return name.length() + " - " + name;
  }
}

class TransformTask extends RecursiveTask<List<String>> {

  private final List<String> names;

  TransformTask(List<String> names) {
    this.names = names;
  }

  @Override
  protected List<String> compute() {
    // BASE CASE: Threshold is 1
    if (names.size() <= 1) {
      return
          names
              .stream()
              .map(StringTransformExampleUsingForkJoinPool::transform)
              .toList();
    }

    // RECURSIVE STEP: Split the list in half
    val mid = names.size() / 2;
    val leftTask = new TransformTask(names.subList(0, mid));
    val rightTask = new TransformTask(names.subList(mid, names.size()));

    // Fork the left side (asynchronous)
    leftTask.fork();

    // Compute the right side in the CURRENT thread (efficiency optimization)
    val rightResult = rightTask.compute();

    // Join the left side (wait for result)
    val leftResult = leftTask.join();

    // Merge results
    val combined = new ArrayList<>(leftResult);
    combined.addAll(rightResult);
    return combined;
  }

}