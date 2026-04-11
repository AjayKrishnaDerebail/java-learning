package com.learnjava.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import lombok.val;
import org.junit.jupiter.api.Test;

public class ProductServiceUsingCompletableFutureTest {

  private final ProductInfoService productInfoService = new ProductInfoService();
  private final ReviewService reviewService = new ReviewService();
  ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(
      productInfoService, reviewService);

  @Test
  void retrieveProductDetails() {

    String productId = "ABC123";
    val product = productService.retrieveProductDetails(productId);

    assertNotNull(product);
    assertEquals(productId, product.getProductInfo().getProductId());

  }

}