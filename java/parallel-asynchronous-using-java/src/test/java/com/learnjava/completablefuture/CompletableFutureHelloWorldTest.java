package com.learnjava.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

public class CompletableFutureHelloWorldTest {

  private static final String HELLO_WORLD = "HELLO WORLD";

  HelloWorldService helloWorldService = new HelloWorldService();
  CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(
      helloWorldService);

  @Test
  void helloWorld() {
    CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

    completableFuture
        .thenAccept(actualResult -> assertEquals(HELLO_WORLD, actualResult)
        ).join();
  }

}
