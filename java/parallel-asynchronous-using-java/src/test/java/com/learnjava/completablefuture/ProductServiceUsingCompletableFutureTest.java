package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  }

}