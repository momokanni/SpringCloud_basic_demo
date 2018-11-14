package com.txhl.product.service;

import com.txhl.product.common.ReduceStockInput;
import com.txhl.product.entity.Product;
import java.util.List;

/**
 * class_name: ProductService
 * package: com.txhl.product.service
 * describe: 商品接口
 * @author: sl
 * creat_date: 2018/10/13
 * creat_time: 22:53
 **/
public interface ProductService {

    List<Product> findOnLine();

    List<Product> findListByProductId(List<String> productId);

    void reduceStock(List<ReduceStockInput> carDTOList);
}
