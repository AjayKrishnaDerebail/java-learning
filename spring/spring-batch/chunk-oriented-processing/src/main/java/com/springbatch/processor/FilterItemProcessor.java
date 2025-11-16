package com.springbatch.processor;

import com.springbatch.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class FilterItemProcessor implements ItemProcessor<Product, Product> {

  @Override
  public Product process(Product item){
    return item.getProductPrice() <= 149500 ? item : null;
  }
}
