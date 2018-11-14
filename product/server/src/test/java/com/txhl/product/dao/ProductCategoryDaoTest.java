package com.txhl.product.dao;

import com.txhl.product.ProductApplicationTests;
import com.txhl.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductCategoryDaoTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryDao.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertTrue(productCategories.size() > 0);
    }
}