package com.springbatch.processor;

import com.springbatch.model.Product;
import java.util.List;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class ProductValidatingProcessor implements Validator<Product> {

  final List<String> validCategories = List.of("Furniture", "Televisions","Smartphones");
  @Override
  public void validate(Product value) throws ValidationException {
    // Validate product category
    if (!validCategories.contains(value.getProductCategory())) {
      throw new ValidationException("Invalid product category: " + value.getProductCategory());
    }
    
    // Validate product price is not negative
    if (value.getProductPrice() < 0) {
      throw new ValidationException("Product price cannot be negative: " + value.getProductPrice());
    }
  }
}
