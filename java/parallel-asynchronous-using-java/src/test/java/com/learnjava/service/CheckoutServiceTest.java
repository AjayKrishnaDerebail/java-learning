package com.learnjava.service;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutServiceTest {

  final PriceValidatorService priceValidatorService = new PriceValidatorService();
  final CheckoutService checkoutService = new CheckoutService(priceValidatorService);

  @BeforeEach
  void setUp() {
    stopWatchReset();
  }

  @Test
  void testCheckoutResponse_Success() {
    val cart = DataSet.createCart(5);

    val response = checkoutService.checkoutResponse(cart);

    assertEquals(CheckoutStatus.SUCCESS, response.getCheckoutStatus());
    assertEquals(0, response.getErrorList().size());
  }

  @Test
  void testCheckoutResponse_WithInvalidItems() {
    val cart = DataSet.createCart(10);

    val response = checkoutService.checkoutResponse(cart);

    assertEquals(CheckoutStatus.FAILURE, response.getCheckoutStatus());
    assertEquals(2, response.getErrorList().size());
  }

  @Test
  void testCheckoutResponse_WithFailureResult() {
    val cart = DataSet.createCart(20);

    val response = checkoutService.checkoutResponse(cart);

    assertEquals(CheckoutStatus.FAILURE, response.getCheckoutStatus());
    assertEquals(3, response.getErrorList().size());
  }

  @Test
  void testCheckoutResponse_EmptyCart() {
    val cart = new Cart();
    cart.setCartItemList(List.of());

    val response = checkoutService.checkoutResponse(cart);

    assertEquals(CheckoutStatus.SUCCESS, response.getCheckoutStatus());
    assertEquals(0, response.getErrorList().size());
  }

}