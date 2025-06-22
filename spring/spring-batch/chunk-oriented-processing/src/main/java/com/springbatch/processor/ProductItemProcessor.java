package com.springbatch.processor;

import com.springbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductItemProcessor implements ItemProcessor<Product, Product> {

  @Override
  public Product process(Product item) {
    System.out.println("Executed processor");
    Double productPrice = item.getProductPrice();
    item.setProductPrice(productPrice - (productPrice * 0.1));
    return item;
  }
}
