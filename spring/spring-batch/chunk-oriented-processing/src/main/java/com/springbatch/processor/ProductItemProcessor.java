package com.springbatch.processor;

import com.springbatch.model.OnlineSalesProduct;
import com.springbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductItemProcessor implements ItemProcessor<Product, OnlineSalesProduct> {

  @Override
  public OnlineSalesProduct process(Product item) {
    OnlineSalesProduct onlineSalesProduct = new OnlineSalesProduct();
    onlineSalesProduct.setProductId(item.getProductId());
    onlineSalesProduct.setProductName(item.getProductName());
    onlineSalesProduct.setProductCategory(item.getProductCategory());
    onlineSalesProduct.setProductPrice(item.getProductPrice());
    onlineSalesProduct.setTaxPercent(item.getProductCategory().equalsIgnoreCase("Smartphones") ? 10 : 5);
    onlineSalesProduct.setSku(item.getProductName());
    onlineSalesProduct.setShippingRate(item.getProductPrice() < 124500 ? 0 : 10);
    System.out.println("Executed OnlineSalesProduct transformation");

    return onlineSalesProduct;
  }
}
