package com.springbatch.mapper;

import com.springbatch.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setProductId(rs.getLong("product_id"));
    product.setProductName(rs.getString("product_name"));
    product.setProductCategory(rs.getString("product_category"));
    product.setProductPrice(rs.getDouble("product_price"));
    return product;
  }
}
