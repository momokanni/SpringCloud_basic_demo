package com.txhl.order.dao;

import com.txhl.order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderMaster,String>{

}
