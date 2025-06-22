package com.springbatch.reader;

import java.util.Iterator;
import java.util.List;
import org.springframework.batch.item.ItemReader;

public class ProductNameItemReader implements ItemReader<String> {

  private final Iterator<String> productListIterator;

  public ProductNameItemReader(List<String> productList) {
    this.productListIterator = productList.iterator();
  }

  @Override
  public String read() {
    return this.productListIterator.hasNext() ? this.productListIterator.next() : null;
  }
}
