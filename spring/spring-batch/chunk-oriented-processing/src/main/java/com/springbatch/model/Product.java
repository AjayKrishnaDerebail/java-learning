package com.springbatch.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
  private Long productId;
  private String productName;
  @Pattern(regexp = "Furniture|Televisions")
  private String productCategory;
  private Double productPrice;

  @Override
  public int hashCode() {
    return Objects.hash(productId, productName, productCategory, productPrice);
  }

  @Override
  public String toString() {
    return "Product{"
        + "productId="
        + productId
        + ", productName='"
        + productName
        + '\''
        + ", productCategory='"
        + productCategory
        + '\''
        + ", productPrice="
        + productPrice
        + '}';
  }
}
