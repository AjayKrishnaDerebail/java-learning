package com.springbatch.model;

import jakarta.validation.constraints.Min;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
  private Long productId;
  private String productName;
  //@Pattern(regexp = "Furniture|Televisions")
  private String productCategory;
  @Min(0)
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
