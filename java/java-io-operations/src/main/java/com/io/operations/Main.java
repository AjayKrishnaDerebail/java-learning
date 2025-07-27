package com.io.operations;

import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    log.info("Hello and welcome!");
    testFile();
  }

  private static void testFile() {
    File file = new File("files/test.csv");
    if(!file.exists()) {
      log.info("File does not exist");
      return;
    }
    log.info("File exists");
  }
}