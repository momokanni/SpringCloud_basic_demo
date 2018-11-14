package com.txhl.product.service.impl;

import com.txhl.product.dao.ProductCategoryDao;
import com.txhl.product.entity.ProductCategory;
import com.txhl.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 *
 * @author Administrator
 * @create 2018-10-13 22:55
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 通过类别编码查找商品类别具体信息
     * @param categoryType
     * @return
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {

        return productCategoryDao.findByCategoryTypeIn(categoryType);
    }
}
