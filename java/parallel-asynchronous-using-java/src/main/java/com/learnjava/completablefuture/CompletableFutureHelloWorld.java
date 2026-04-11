package com.learnjava.completablefuture;

import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CompletableFutureHelloWorld {

  private final HelloWorldService helloWorldService;

  public CompletableFuture<String> helloWorld(final HelloWorldService helloWorldService){
    //noinspection Convert2MethodRef
    return CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
        .thenApply(String::toUpperCase)
        //.thenAccept(result -> log("Result is " + result))
        //.join()
        ;
  }

  static void main() {
    val helloWorldService = new HelloWorldService();

    //noinspection Convert2MethodRef
    CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
        .thenApply(String::toUpperCase)
        .thenAccept(result -> log("Result is " + result))
        .join();

    log("Done");
  }

}