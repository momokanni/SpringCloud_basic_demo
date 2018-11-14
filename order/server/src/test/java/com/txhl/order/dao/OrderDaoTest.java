package com.txhl.order.dao;

import com.txhl.order.OrderApplicationTests;
import com.txhl.order.entity.OrderMaster;
import com.txhl.order.enums.OrderStatusEnums;
import com.txhl.order.enums.PayStatusEnums;
import com.txhl.order.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class OrderDaoTest extends OrderApplicationTests{

    @Autowired
    private OrderDao orderDao;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(KeyUtil.getUniqueKey());
        orderMaster.setBuyerName("孙林");
        orderMaster.setBuyerPhone("18615204581");
        orderMaster.setBuyerAddress("北京市朝阳区大悦城");
        orderMaster.setBuyerOpenid("ouZVz1FXA-H0iMVUkFm3gkNtQF0s");
        orderMaster.setOrderAmount(new BigDecimal(30));
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        OrderMaster result = orderDao.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}