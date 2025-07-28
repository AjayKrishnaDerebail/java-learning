package com.io.operations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.FileChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassicJavaFileHandling {

  public static void main(String[] args) {
    String path = "files/test2.csv";
    regularReadOfFile(path);
    readUsingBufferedReader(path);
  }

  private static void regularReadOfFile(String path) {
    // Using FileInputStream with FileChannel to control file position
    try (FileInputStream fis = new FileInputStream(path);
        FileChannel fileChannel = fis.getChannel();
        Reader reader = new InputStreamReader(fis)) {

      // First read: character by character
      int data;
      while ((data = reader.read()) != -1) {
        System.out.print((char) data);
      }

      System.out.println();

      // Reset file pointer to beginning using FileChannel
      fileChannel.position(0);

      // Second read: block reading with reset position
      char[] block = new char[1000];
      while ((data = reader.read(block)) != -1) {
        String content = new String(block, 0, data);
        log.info("----> [{} chars] {}", data, content);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }
  }

  private static void readUsingBufferedReader(String path) {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      reader.lines().forEach(log::info);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}