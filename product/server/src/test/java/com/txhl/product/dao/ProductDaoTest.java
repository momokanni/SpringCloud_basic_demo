package com.txhl.product.dao;

import com.txhl.product.ProductApplicationTests;
import com.txhl.product.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductDaoTest extends ProductApplicationTests {

    @Autowired
    private ProductDao productDao;

    @Test
    public void findProductByProductStatusIn() {
        List<Product> products = productDao.findProductByProductStatusIn(0);
        Assert.assertTrue(products.size()>0);
    }

    @Test
    public void findProductsByProductIdIn(){
        List<Product> products = productDao.findProductsByProductIdIn(Arrays.asList("1524735277972427708"));
        Assert.assertTrue(products.size()>0);
    }
}