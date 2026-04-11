package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductServiceUsingCompletableFuture {

  private final ProductInfoService productInfoService;
  private final ReviewService reviewService;

  public Product retrieveProductDetails(String productId) {
    stopWatch.start();

    CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(
        () -> productInfoService.retrieveProductInfo(productId));

    CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(
        () -> reviewService.retrieveReviews(productId));

    Product product = productInfoCompletableFuture
        .thenCombine(reviewCompletableFuture,
            (productInfo, review) -> new Product(productId, productInfo, review))
        .join();

    stopWatch.stop();
    log("Total Time Taken : " + stopWatch.getTime());
    return product;
  }

  static void main() {

    ProductInfoService productInfoService = new ProductInfoService();
    ReviewService reviewService = new ReviewService();
    ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(
        productInfoService, reviewService);
    String productId = "ABC123";
    Product product = productService.retrieveProductDetails(productId);
    log("Product is " + product);

  }
}