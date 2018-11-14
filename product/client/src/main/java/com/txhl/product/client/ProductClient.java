package com.txhl.product.client;

import com.txhl.product.common.ProductOutput;
import com.txhl.product.common.ReduceStockInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * class_name: ProductClient
 * describe: Product对外暴露的端口
 * @author: sl
 * @create: 2018/10/16
 **/
@FeignClient(name = "product",fallback = ProductClientFallBack.class)
public interface ProductClient {

    @PostMapping(value = "/getProductList")
    List<ProductOutput> listForOrder(@RequestBody List<String> productIds);

    @PostMapping(value = "/reduceStock")
    void reduceStock(@RequestBody List<ReduceStockInput> reduceStockInputs);
}
