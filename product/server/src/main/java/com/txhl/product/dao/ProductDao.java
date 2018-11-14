package com.txhl.product.dao;

import com.txhl.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,String> {

    List<Product> findProductByProductStatusIn(Integer productStatus);

    List<Product> findProductsByProductIdIn(List<String> productId);
}
