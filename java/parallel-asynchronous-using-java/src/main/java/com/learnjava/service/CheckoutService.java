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
          val isPriceValid = priceValidatorService.isCartItemInvalid(cartItem);
          cartItem.setExpired(isPriceValid);
        })
        .filter(CartItem::isExpired)
        .toList();
    timeTaken();

    if (!priceValidationList.isEmpty()) {
      return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
    }

    val finalPrice = calculateFinalPrice(cart);

    log.info("Final price is : {}", finalPrice);

    return new CheckoutResponse(CheckoutStatus.SUCCESS,finalPrice);
  }

  private double calculateFinalPrice(final Cart cart){
    return cart.getCartItemList()
        .parallelStream()
        .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
        .mapToDouble(Double::doubleValue)
        .sum();
  }

  @SuppressWarnings("unused")
  private double calculateFinalPriceUsingReduce(final Cart cart){
    return cart.getCartItemList()
        .parallelStream()
        .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
        .reduce(0.0 , Double::sum);
        //reduce(0.0 , (x,y) -> x +y);
  }

}