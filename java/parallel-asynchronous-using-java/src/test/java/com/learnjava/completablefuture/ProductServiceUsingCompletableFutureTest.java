package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class ProductServiceUsingCompletableFutureTest {

  private final ProductInfoService productInfoService = new ProductInfoService();
  private final ReviewService reviewService = new ReviewService();
  private final InventoryService inventoryService = new InventoryService();
  ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(
      productInfoService, reviewService, inventoryService);

  @BeforeEach
  void setUp() {
    stopWatchReset();
  }

  @Test
  void retrieveProductDetails() {

    String productId = "ABC123";
    val product = productService.retrieveProductDetails(productId);

    assertNotNull(product);
    assertEquals(productId, product.getProductInfo().getProductId());

    log.info("Retrieve product details");

  }

  @Test
  void retrieveProductDetailsServerStyle() {

    String productId = "ABC123";
    val product = productService.retrieveProductDetailsServerStyleProgramming(productId);

    product.thenAccept(
        p ->{
          assertNotNull(p);
          assertEquals(productId, p.getProductInfo().getProductId());
        }
    );

    log.info("Retrieve product details server style programming");
  }

  @Test
  void retrieveProductDetailsWithInventory() {

    String productId = "ABC123";
    val product = productService.retrieveProductDetailsWithInventory(productId);

    assertNotNull(product);
    assertEquals(4 , product.getProductInfo().getProductOptions().size());
    product.getProductInfo().getProductOptions()
        .forEach(productOption ->
            assertNotNull(productOption.getInventory()));

    log.info("Retrieve product details with inventory");

  }

  @Test
  void retrieveProductDetailsWithInventory_CF() {

    String productId = "ABC123";
    val product = productService.retrieveProductDetailsWithInventoryCF(productId);

    assertNotNull(product);
    assertEquals(4 , product.getProductInfo().getProductOptions().size());
    product.getProductInfo().getProductOptions()
        .forEach(productOption ->
            assertNotNull(productOption.getInventory()));

    log.info("Retrieve product details with inventory CF");

  }

}