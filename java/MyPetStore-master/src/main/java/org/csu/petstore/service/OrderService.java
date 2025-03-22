package org.csu.petstore.service;

import org.csu.petstore.entity.LineItem;
import org.csu.petstore.entity.Order;
import org.csu.petstore.entity.OrderStatus;
import org.csu.petstore.vo.AccountVO;
import org.csu.petstore.vo.CartItemListMapper;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUsername(String username);
    Order getOrderByOrderId(int orderId);
    List<OrderStatus> getOrderStatusByOrderId(int orderId);
    List<LineItem> getOrderLineItemByOrderId(int orderId);
    int insertOrder(Order order, AccountVO account);
    void finishOrder(Order order, CartItemListMapper cartItemListMapper);
}