package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompletableFutureHelloWorldTest {

  private static final String HELLO_WORLD = "HELLO WORLD";
  private static final String HELLO_WORLD_EXCLAMATION = "HELLO WORLD!";
  private static final String HELLO_WORLD_EXCLAMATION_HI_CF = "HELLO WORLD! HI COMPLETABLEFUTURE";
  private static final String HELLO_WORLD_APPEND_LENGTH = "11 - HELLO WORLD";

  HelloWorldService helloWorldService = new HelloWorldService();
  CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(
      helloWorldService);

  @BeforeEach
  void setUp(){
    stopWatchReset();
  }

  @Test
  void helloWorld() {
    CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

    completableFuture
        .thenAccept(actualResult -> assertEquals(HELLO_WORLD, actualResult)
        ).join();
  }

  @Test
  void helloWorldAppendStringLength() {
    CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorldAppendStringLength();

    completableFuture
        .thenAccept(actualResult -> assertEquals(HELLO_WORLD_APPEND_LENGTH, actualResult)
        ).join();
  }

  @Test
  void helloWorldThenCombine() {
    val completableFutureThenCombineResult = completableFutureHelloWorld.completableFutureThenCombine();

    assertEquals(HELLO_WORLD_EXCLAMATION, completableFutureThenCombineResult);
  }

  @Test
  void helloWorldThenCombine3Cf() {
    val completableFutureThenCombineResult = completableFutureHelloWorld.completableFutureThenCombine3Cfs();

    assertEquals(HELLO_WORLD_EXCLAMATION_HI_CF, completableFutureThenCombineResult);
  }

  @Test
  void helloWorldThenCompose() {
    val completableFutureThenCombineResult = completableFutureHelloWorld.completableFutureThenCompose();

    completableFutureThenCombineResult.thenAccept(
        result -> assertEquals(HELLO_WORLD_EXCLAMATION , result)
    ).join();
  }

}