package org.virtual.threads.section1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task {

  public static void ioIntensiveTask(final Integer ioTask) {
    try {
      log.info("IO Intensive Task {} started", ioTask);
      Thread.sleep(3000);
      log.info("IO Intensive Task {} completed", ioTask);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}