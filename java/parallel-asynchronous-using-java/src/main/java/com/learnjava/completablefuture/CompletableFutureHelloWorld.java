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
public class CompletableFutureHelloWorld {

  private final HelloWorldService helloWorldService;

  public CompletableFuture<String> helloWorld(){
    //noinspection Convert2MethodRef
    return CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
        .thenApply(String::toUpperCase)
        //.thenAccept(result -> log("Result is " + result))
        //.join()
        ;
  }

  public CompletableFuture<String> helloWorldAppendStringLength(){
    //noinspection Convert2MethodRef
    return CompletableFuture.supplyAsync(() -> helloWorldService.helloWorld())
        .thenApply(String::toUpperCase)
        .thenApply(result -> result.length() + " - " + result)
        //.thenAccept(result -> log("Result is " + result))
        //.join()
        ;
  }

  @SuppressWarnings("Convert2MethodRef")
  public String completableFutureThenCombine(){

    startTimer();

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());

    val result = hello.thenCombine(world,(h,w) -> h + w)
        .thenApply(String::toUpperCase)
        .join();

    timeTaken();

    return result;
  }

  @SuppressWarnings("Convert2MethodRef")
  public String completableFutureThenCombine3Cfs(){

    startTimer();
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> helloWorldService.hello());
    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> helloWorldService.world());
    CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> helloWorldService.hi());

    val result = hello.thenCombine(world,(h,w) -> h + w)
        .thenCombine(hi,(helloWorld,hiCf) -> helloWorld + hiCf)
        .thenApply(String::toUpperCase)
        .join();

    timeTaken();

    return result;
  }

  @SuppressWarnings("Convert2MethodRef")
  public CompletableFuture<String> completableFutureThenCompose(){

    startTimer();

    val result = CompletableFuture.supplyAsync(() -> helloWorldService.hello())
            .thenCompose(hello -> helloWorldService.worldFuture(hello))
            .thenApply(String::toUpperCase);
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