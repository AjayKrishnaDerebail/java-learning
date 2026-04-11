package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CompletableFutureHelloWorldException {

  private final HelloWorldService helloWorldService;

  @SuppressWarnings("Convert2MethodRef")
  public String completableFutureThenCombine3CfsUsingHandle() {

    startTimer();
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(
        () -> helloWorldService.hello());
    CompletableFuture<String> world = CompletableFuture.supplyAsync(
        () -> helloWorldService.world());
    CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> helloWorldService.hi());

    val result = hello
        .handle((res, e) -> {
          if (e != null) {
            log("Exception is : " + e.getMessage());
            return "";
          }
          return res;
        })
        .thenCombine(world, (h, w) -> h + w)
        .handle((res, e) -> {
          if (e != null) {
            log("Exception during world is : " + e.getMessage());
            return "";
          }
          return res;
        })
        .thenCombine(hi, (helloWorld, hiCf) -> helloWorld + hiCf)
        .thenApply(String::toUpperCase)
        .join();

    timeTaken();

    return result;
  }

  @SuppressWarnings("Convert2MethodRef")
  public String completableFutureThenCombine3CfsUsingExceptionally() {

    startTimer();
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(
        () -> helloWorldService.hello());
    CompletableFuture<String> world = CompletableFuture.supplyAsync(
        () -> helloWorldService.world());
    CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> helloWorldService.hi());

    val result = hello
        .exceptionally(( e) -> {
            log("Exception is : " + e.getMessage());
          return "";
        })
        .thenCombine(world, (h, w) -> h + w)
        .exceptionally(( e) -> {
            log("Exception during world is : " + e.getMessage());
          return "";
        })
        .thenCombine(hi, (helloWorld, hiCf) -> helloWorld + hiCf)
        .thenApply(String::toUpperCase)
        .join();

    timeTaken();

    return result;
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