package org.virtual.threads.section2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class AccessResponseUsingFuture {

  static void main() throws ExecutionException, InterruptedException {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      Future<String> productFuture = executor.submit(() -> Client.getProduct(1));
      log.info("The product is : {}", productFuture.get());
    }

    val result2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2)).join();
    val result3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3)).join();
    log.info(result2);
    log.info(result3);
  }

}