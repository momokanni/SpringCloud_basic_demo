package com.txhl.product.client;

import com.txhl.product.common.ProductOutput;
import com.txhl.product.common.ReduceStockInput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-11-13 0:21
 */
@Component
public class ProductClientFallBack implements ProductClient {

    @Override
    public List<ProductOutput> listForOrder(List<String> productIds) {
        return null;
    }

    @Override
    public void reduceStock(List<ReduceStockInput> reduceStockInputs) {

    }
}
