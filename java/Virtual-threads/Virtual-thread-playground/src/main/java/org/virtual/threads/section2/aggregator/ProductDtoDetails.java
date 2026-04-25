package org.virtual.threads.section2.aggregator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.virtual.threads.section2.dto.ProductDto;

@Slf4j
public class ProductDtoDetails {

  static void main() {

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      //To get one productDto
      ProductRatingAggregator aggregator = new ProductRatingAggregator(executor);
      ProductDto productDto = aggregator.getProductInfo(1);
      log.info(productDto.toString());

      //To get more productDto's
      val futuresList = IntStream.rangeClosed(1, 50)
          .mapToObj(id -> executor.submit(() -> aggregator.getProductInfo(id)))
          .toList();

      val list = futuresList.stream()
          .map(ProductDtoDetails::toProductDto)
          .toList();

      list.forEach(System.out::println);
    }

  }

  private static ProductDto toProductDto(Future<ProductDto> productDto) {
    try {
      return productDto.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

}