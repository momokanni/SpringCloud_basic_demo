package com.txhl.product.service.impl;

import com.txhl.product.ProductApplicationTests;
import com.txhl.product.common.ReduceStockInput;
import com.txhl.product.entity.Product;
import com.txhl.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findOnLine() {
        List<Product> products = productService.findOnLine();
        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void findListByProductId(){
        List<Product> products = productService.findListByProductId(Arrays.asList("1524735277972427708","1524800799092345144"));
        Assert.assertTrue(products.size()>0);
    }

    @Test
    public void reduceStock(){
        List<ReduceStockInput> carDTOList = new ArrayList<>();

        ReduceStockInput carDTO = new ReduceStockInput("1524800224429470815",1);
        ReduceStockInput carDTO2 = new ReduceStockInput("1524800892136358889",2);

        carDTOList.add(carDTO);
        carDTOList.add(carDTO2);

        productService.reduceStock(carDTOList);
    }
}