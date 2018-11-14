package com.txhl.product.service.impl;

import com.txhl.product.common.ProductOutput;
import com.txhl.product.common.ReduceStockInput;
import com.txhl.product.dao.ProductDao;
import com.txhl.product.entity.Product;
import com.txhl.product.common.enums.ProductStatus;
import com.txhl.product.enums.ResultEnums;
import com.txhl.product.expections.ProductException;
import com.txhl.product.service.ProductService;
import com.txhl.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 商品业务
 *
 * @author Administrator
 * @create 2018-10-13 22:41
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 查找所有在线商品
     * @return
     */
    @Override
    public List<Product> findOnLine() {

        return productDao.findProductByProductStatusIn(ProductStatus.NORMAL.getCode());
    }

    @Override
    public List<Product> findListByProductId(List<String> productId) {
        return productDao.findProductsByProductIdIn(productId);
    }

    @Override
    public void reduceStock(List<ReduceStockInput> carDTOList) {
        List<Product> list = doReduceStock(carDTOList);
        // 发送消息
        List<ProductOutput> outputList = list.stream().map(e -> {
            ProductOutput productOutput = new ProductOutput();
            BeanUtils.copyProperties(e,productOutput);
            return productOutput;
        }).collect(Collectors.toList());

        amqpTemplate.convertAndSend("productQueue", JsonUtil.toJson(outputList));
    }


    @Transactional
    public List<Product> doReduceStock(List<ReduceStockInput> carDTOList) {
        List<Product> productList = new ArrayList<>();
        for (ReduceStockInput reduceStockInput : carDTOList) {
            Optional<Product> productOptional = productDao.findById(reduceStockInput.getProductId());
            if (!productOptional.isPresent()) {
                throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            Product product = productOptional.get();
            int stock = product.getProductStock() - reduceStockInput.getProductQuantity();
            if (stock < 0){
                throw new ProductException(ResultEnums.PRODUCT_LACK_STOCK);
            }

            product.setProductStock(stock);
            productDao.save(product);

            productList.add(product);

        }
        return productList;
    }
}
