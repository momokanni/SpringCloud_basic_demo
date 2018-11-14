package com.txhl.order.dao;

import com.txhl.order.OrderApplicationTests;
import com.txhl.order.entity.OrderDetail;
import com.txhl.order.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailDaoTest extends OrderApplicationTests{

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId("1539503173832977202");
        orderDetail.setProductIcon("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=1998ec37c1ef76093c0b9e9916e6c4f1/78310a55b319ebc4a2299e338826cffc1f1716c2.jpg");
        orderDetail.setProductId("565639");
        orderDetail.setProductName("珍珠奶茶");
        orderDetail.setProductPrice(new BigDecimal(15.00));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertTrue(result != null);
    }
}