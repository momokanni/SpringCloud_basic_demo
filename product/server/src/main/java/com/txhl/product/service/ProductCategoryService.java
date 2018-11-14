package com.txhl.product.service;

import com.txhl.product.entity.ProductCategory;
import java.util.List;

/**
 * class_name: ProductCategoryService
 * package: com.txhl.product.service
 * describe: 类目接口
 * @author: sl
 * creat_date: 2018/10/13
 * creat_time: 22:52
 **/
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
