package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatusManageMapper {

    // 获取所有订单状态
    List<OrderStatus> getOrderStatusList();

    // 根据订单ID获取订单状态
    OrderStatus getOrderStatusByOrderId(int orderId);

    // 添加订单状态
    void addOrderStatus(OrderStatus orderStatus);

    // 更新订单状态

    void updateOrderStatus(int orderId,String status);

    // 删除订单状态
    void deleteOrderStatus(int orderId);

    // 根据状态查询订单状态
    List<OrderStatus> searchOrderStatusByStatus(String status);
}