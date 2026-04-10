package com.learnjava.service;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@RequiredArgsConstructor
@Slf4j
public class CheckoutService {

  static void main() {
    val checkoutService = new CheckoutService(new PriceValidatorService());
    val response = checkoutService.checkoutResponse(DataSet.createCart(100));

    log.info("Checkout result is : {}", response);
  }

  private final PriceValidatorService priceValidatorService;

  public CheckoutResponse checkoutResponse(final Cart cart) {
    startTimer();
    val priceValidationList = cart.getCartItemList()
        .parallelStream()
        .peek(cartItem -> {
          boolean isPriceValid = priceValidatorService.isCartItemInvalid(cartItem);
          cartItem.setExpired(isPriceValid);
        })
        .filter(CartItem::isExpired)
        .toList();
    timeTaken();

    if (!priceValidationList.isEmpty()) {
      return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
    }

    return new CheckoutResponse(CheckoutStatus.SUCCESS);
  }
}