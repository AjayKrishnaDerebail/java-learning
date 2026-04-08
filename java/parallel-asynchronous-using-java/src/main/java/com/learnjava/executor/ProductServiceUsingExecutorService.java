package com.learnjava.executor;

import static com.learnjava.util.CommonUtil.stopWatch;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class ProductServiceUsingExecutorService {

  private static final ExecutorService executorService = Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors());
  private ProductInfoService productInfoService;
  private ReviewService reviewService;

  public ProductServiceUsingExecutorService(ProductInfoService productInfoService,
      ReviewService reviewService) {
    this.productInfoService = productInfoService;
    this.reviewService = reviewService;
  }

  public Product retrieveProductDetails(String productId)
      throws ExecutionException, InterruptedException, TimeoutException {
    log.info("Available cores is {} " , Runtime.getRuntime().availableProcessors());
    stopWatch.start();

    Future<ProductInfo> productInfoFuture = executorService.submit(
        () -> productInfoService.retrieveProductInfo(productId));
    Future<Review> reviewFuture = executorService.submit(
        () -> reviewService.retrieveReviews(productId));

    val productInfo = productInfoFuture.get();
    val review = reviewFuture.get(1, TimeUnit.MILLISECONDS);

    executorService.shutdownNow();

    stopWatch.stop();
    log.info("Total Time Taken : " + stopWatch.getTime());
    return new Product(productId, productInfo, review);
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

    ProductInfoService productInfoService = new ProductInfoService();
    ReviewService reviewService = new ReviewService();
    ProductServiceUsingExecutorService productService = new ProductServiceUsingExecutorService(
        productInfoService, reviewService);
    String productId = "ABC123";
    Product product = productService.retrieveProductDetails(productId);
    log.info("Product is " + product.getProductInfo() + " \n Review is " + product.getReview());

  }
}