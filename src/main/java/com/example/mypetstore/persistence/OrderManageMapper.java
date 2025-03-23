package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.OrderManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderManageMapper {

    // 获取所有订单
    List<OrderManage> getOrderManageList();

    // 根据订单ID获取订单
    OrderManage getOrderManageById(int orderId);

    // 添加订单
    void addOrderManage(OrderManage order);

    // 更新订单
    void updateOrderManage(OrderManage order);

    // 删除订单
    void deleteOrderManage(int orderId);

    // 根据用户ID查询订单
    List<OrderManage> searchOrderManageByUserId(int userId);

    // 根据订单状态查询订单
    List<OrderManage> searchOrderManageByStatus(String status);
}