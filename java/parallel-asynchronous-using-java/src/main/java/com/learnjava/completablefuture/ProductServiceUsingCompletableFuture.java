package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.ProductOption;
import com.learnjava.domain.Review;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.val;

public class ProductServiceUsingCompletableFuture {

  private final ProductInfoService productInfoService;
  private final ReviewService reviewService;
  private InventoryService inventoryService;

  public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService,
      ReviewService reviewService) {
    this.productInfoService = productInfoService;
    this.reviewService = reviewService;
  }

  public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService,
      ReviewService reviewService, InventoryService inventoryService) {
    this.productInfoService = productInfoService;
    this.reviewService = reviewService;
    this.inventoryService = inventoryService;
  }

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

  public CompletableFuture<Product> retrieveProductDetailsServerStyleProgramming(String productId) {
    stopWatch.start();

    CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(
        () -> productInfoService.retrieveProductInfo(productId));

    CompletableFuture<Review> reviewCompletableFuture = CompletableFuture.supplyAsync(
        () -> reviewService.retrieveReviews(productId));

    val product = productInfoCompletableFuture
        .thenCombine(reviewCompletableFuture,
            (productInfo, review) -> new Product(productId, productInfo, review));

    stopWatch.stop();
    log("Total Time Taken : " + stopWatch.getTime());
    return product;
  }


  public Product retrieveProductDetailsWithInventory(String productId) {
    stopWatch.start();

    CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(
        () -> productInfoService.retrieveProductInfo(productId))
        .thenApply(productInfo -> {
          productInfo.setProductOptions(updateInventory(productInfo));
          return productInfo;
        });

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

  public Product retrieveProductDetailsWithInventoryCF(String productId) {
    stopWatch.start();

    CompletableFuture<ProductInfo> productInfoCompletableFuture = CompletableFuture.supplyAsync(
            () -> productInfoService.retrieveProductInfo(productId))
        .thenApply(productInfo -> {
          productInfo.setProductOptions(updateInventoryCF(productInfo));
          return productInfo;
        });

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

  @SuppressWarnings("SimplifyStreamApiCallChains")
  private List<ProductOption> updateInventory(final ProductInfo productInfo){
    return productInfo.getProductOptions()
        .stream()
        .map(productOption -> {
          val inventory = inventoryService.retrieveInventory(productOption);
          productOption.setInventory(inventory);
          return productOption;
        })
        .toList();
  }

  private List<ProductOption> updateInventoryCF(final ProductInfo productInfo){
    List<CompletableFuture<ProductOption>> productOptionCF = productInfo.getProductOptions()
        .stream()
        .map(productOption -> CompletableFuture.supplyAsync(
                () -> inventoryService.retrieveInventory(productOption))
            .thenApply(inventory -> {
              productOption.setInventory(inventory);
              return productOption;
            }))
        .toList();

    return productOptionCF.stream()
        .map(CompletableFuture::join)
        .toList();
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