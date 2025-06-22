package com.springbatch.processor;

import com.springbatch.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class FilterItemProcessor implements ItemProcessor<Product, Product> {

  @Override
  public Product process(Product item){
    return item.getProductPrice() <= 149500 ? item : null;
  }
}
