package com.txhl.product.controller;

import com.txhl.product.entity.Product;
import com.txhl.product.entity.ProductCategory;
import com.txhl.product.service.ProductCategoryService;
import com.txhl.product.service.ProductService;
import com.txhl.product.utils.ResultUtils;
import com.txhl.product.vo.CategoryVO;
import com.txhl.product.vo.ProductVO;
import com.txhl.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品处理器
 *
 * @author sl
 * @create 2018-10-12 14:13
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 上线商品列表
     * @return
     */
    @GetMapping(value = "/list")
    public ResultVO list(){
        // 查找上线商品
        List<Product> onLineProduct = productService.findOnLine();
        // 通过商品获取类目集合
        List<Integer> categoryTypes = onLineProduct.stream().map(e -> e.getCategoryType())
                                      .collect(Collectors.toList());
        // 查询类目列表
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(categoryTypes);
        // 拼装数据
        List<CategoryVO> categoryVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryName(productCategory.getCategoryName());
            categoryVO.setCategoryType(productCategory.getCategoryType());
            List<ProductVO> products = new ArrayList<>();
            for (Product product : onLineProduct) {
                if (product.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product,productVO);
                    products.add(productVO);
                }
            }
            categoryVO.setProductInfoVOList(products);
            categoryVOS.add(categoryVO);
        }
        return ResultUtils.success(categoryVOS);
    }
}
