package com.io.operations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    //log.info("Hello and welcome!");
    //testFile();
    //testFileUsingPath();
    useFile();
  }

  private static void testFile() {
    File file = new File("files/test.csv");
    if (!file.exists()) {
      log.info("File does not exist");
      return;
    }
    log.info("File exists , file path {}", file.getAbsolutePath());
  }

  private static void testFileUsingPath() {
    File file = new File("files/test.csv");
    Path path = Paths.get("files/test.csv");
    if (!Files.exists(path)) {
      log.info("File does not exist using path");
      return;
    }
    log.info("File exists using path , file path is : {}", file.getAbsolutePath());
  }

  private static void useFile() {
    File file = new File("files/test.csv");
    boolean fileExists = false;
    if (file.exists()) {
      log.info("File existed deleting current file");
      fileExists = !file.delete();
    }
    if (!fileExists) {
      log.info("Creating new file");
      try {
        boolean created = file.createNewFile();
        log.info("File created : {} , in path : {}", created, file.getAbsolutePath());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    log.info("File exists , file path {}", file.getAbsolutePath());
    if(file.canWrite()){
      log.info("File is writable");
    }
  }

}