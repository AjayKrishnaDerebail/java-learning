package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ProductServiceUsingCompletableFutureExceptionTest {

  @Mock
  private ProductInfoService productInfoService;

  @Mock
  private ReviewService reviewService;

  @Mock
  private InventoryService inventoryService;

  @InjectMocks
  ProductServiceUsingCompletableFuture productService;

  @BeforeEach
  void setUp() {
    stopWatchReset();
  }

  @Test
  void retrieveProductDetailsWithInventory_ReviewServiceException() {

    String productId = "ABC123";

    when(productInfoService.retrieveProductInfo(productId)).thenCallRealMethod();
    when(inventoryService.retrieveInventory(any())).thenCallRealMethod();
    when(reviewService.retrieveReviews(productId)).thenThrow(
        new RuntimeException("Exception in review service"));

    val product = productService.retrieveProductDetailsWithInventory(productId);

    assertNotNull(product);
    assertEquals(4, product.getProductInfo().getProductOptions().size());
    product.getProductInfo().getProductOptions()
        .forEach(productOption -> assertNotNull(productOption.getInventory()));
    assertEquals(0, product.getReview().getNoOfReviews());

    log.info("ReviewService exception handled - default review with 0 reviews returned");

  }

}