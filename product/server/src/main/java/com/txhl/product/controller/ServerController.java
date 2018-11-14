package com.txhl.product.controller;

import com.txhl.product.common.ReduceStockInput;
import com.txhl.product.entity.Product;
import com.txhl.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 商品对外服务接口
 *
 * @author Administrator
 * @create 2018-10-14 16:10
 */

@RestController
@Slf4j
public class ServerController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "msg")
    public String getProductMsg(){

        return "connect Product Servcer Success";
    }

    @PostMapping(value = "/getProductList")
    public List<Product> getProductList(@RequestBody List<String> productId){

        List<Product> products = productService.findListByProductId(productId);
        return  products;
    }

    @PostMapping(value = "/reduceStock")
    public void reduceStock(@RequestBody List<ReduceStockInput> reduceStockInputs){

        productService.reduceStock(reduceStockInputs);
    }
}
