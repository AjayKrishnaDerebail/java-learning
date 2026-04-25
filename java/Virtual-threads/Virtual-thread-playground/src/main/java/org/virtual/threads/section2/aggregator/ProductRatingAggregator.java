package org.virtual.threads.section2.aggregator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.virtual.threads.section2.Client;
import org.virtual.threads.section2.dto.ProductDto;

@Slf4j
@RequiredArgsConstructor
public class ProductRatingAggregator {

  private final ExecutorService executor;

  public ProductDto getProductInfo(final int id) {
    try {
      Future<String> productFuture = executor.submit(() -> Client.getProduct(id));
      Future<String> ratingsFuture = executor.submit(() -> Client.getRating(id));

      return new ProductDto(id, productFuture.get(), ratingsFuture.get());
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}