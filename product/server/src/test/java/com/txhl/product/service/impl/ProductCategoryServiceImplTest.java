package com.txhl.product.service.impl;

import com.txhl.product.ProductApplicationTests;
import com.txhl.product.entity.ProductCategory;
import com.txhl.product.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductCategoryServiceImplTest extends ProductApplicationTests{

    @Autowired
    private ProductCategoryService productCategoryService;


    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(Arrays.asList(2,3,4));
        assertTrue(productCategories.size() > 0);
    }
}