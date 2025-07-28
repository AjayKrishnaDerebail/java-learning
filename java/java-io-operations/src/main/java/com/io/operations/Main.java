package com.io.operations;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) throws IOException {
    //log.info("Hello and welcome!");
    //testFile();
    //testFileUsingPath("files/test2."csv");
    //useFile();
    //usePath("files/test2.csv");
    Path path = Path.of("");
    //getFileMetadata(path);
    directoryListingMaster(path);
  }

  private static void testFile() {
    File file = new File("files/test.csv");
    if (!file.exists()) {
      log.info("File does not exist");
      return;
    }
    log.info("File exists , file path {}", file.getAbsolutePath());
  }

  private static void testFileUsingPath(String pathOfFile) {
    File file = new File(pathOfFile);
    Path path = Paths.get(pathOfFile);
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
    if (file.canWrite()) {
      log.info("File is writable");
    }
  }

  private static void usePath(String pathOfFile) {
    Path path = Path.of(pathOfFile);
    boolean fileExists = false;
    if (Files.exists(path)) {
      log.info("File existed deleting current file");
      try {
        Files.delete(path);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    if (!fileExists) {
      log.info("Creating new file");
      try {
        Path createdFile = Files.createFile(path);
        log.info("File created : {} , in path : {}", createdFile, createdFile.getFileName());
        if (Files.isWritable(path)) {
          log.info("File is writable");
          Files.writeString(path, """
              Hello this is a test file created
              Its created using Files and paths API""", UTF_8);
        }
        log.info("-------------------------------------------------------");
        Files.readAllLines(path).forEach(log::info);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static void getFileMetadata(Path path) throws IOException {
    log.info("File name is : {}", path.getFileName());
    log.info("File parent is  : {}", path.getParent());
    Path absolutePath = path.toAbsolutePath();
    log.info("File absolute path is : {}", absolutePath);
    log.info("File root is : {}", absolutePath.getRoot());
    log.info("File size is : {}", Files.size(path));
    log.info("File last modified is : {}", Files.getLastModifiedTime(path));
    log.info("File is writable : {}", Files.isWritable(path));
    log.info("File is readable : {}", Files.isReadable(path));
    log.info("File is executable : {}", Files.isExecutable(path));
    log.info("File is directory : {}", Files.isDirectory(path));
    log.info("File is hidden : {}", Files.isHidden(path));
    log.info("File is absolute : {}", path.isAbsolute());
    log.info("File is symbolic link : {}", Files.isSymbolicLink(path));
    log.info("File is regular file : {}", Files.isRegularFile(path));

    var iterator = path.toAbsolutePath().iterator();
    int i = 1;
    while (iterator.hasNext()) {
      log.info(".".repeat(i++) + " " + iterator.next());
    }

    log.info("-------------------------------------------------------");
  }

  private static void directoryListingMaster(Path directoryPath){

    try(Stream<Path> pathStream = Files.walk(directoryPath,2)){
      pathStream
          .filter(file -> file.toFile().isDirectory())
          .filter(Files :: isDirectory)
          .peek(file -> System.out.print("Directory : " + file.getFileName() + " "))
          .forEach(file -> System.out.println(file.getFileName()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}