package com.springbatch.processor;

import com.springbatch.model.Product;
import java.util.List;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class ProductValidatingProcessor implements Validator<Product> {

  final List<String> validCategories = List.of("Furniture", "Televisions");
  @Override
  public void validate(Product value) throws ValidationException {
    if (!validCategories.contains(value.getProductCategory())) {
      throw new ValidationException("Product category is not valid");
    }
  }
}
