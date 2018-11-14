package com.txhl.product.dao;

import com.txhl.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,String> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
