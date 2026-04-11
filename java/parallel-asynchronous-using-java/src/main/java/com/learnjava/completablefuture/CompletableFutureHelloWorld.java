package com.learnjava.completablefuture;

import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHelloWorld {

  static void main() {
    HelloWorldService helloWorldService = new HelloWorldService();

    //noinspection Convert2MethodRef
    CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
        .thenApply(String::toUpperCase)
        .thenAccept(result -> log("Result is " + result))
        .join();

    log("Done");
  }

}
