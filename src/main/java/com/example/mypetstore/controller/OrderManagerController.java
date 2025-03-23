package com.example.mypetstore.controller;

import com.example.mypetstore.domain.OrderManage;
import com.example.mypetstore.domain.OrderStatus;
import com.example.mypetstore.persistence.LineItemManageMapper;
import com.example.mypetstore.persistence.OrderManageMapper;
import com.example.mypetstore.persistence.OrderStatusManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/petManage") // 基础路径
public class OrderManagerController {

    @Autowired
    public OrderManageMapper orderManageMapper; // 注入 OrderManageMapper
    @Autowired
    private OrderStatusManageMapper orderStatusManageMapper;
    @Autowired
    private LineItemManageMapper lineItemManageMapper;


    /**
     * 展示所有订单
     */
    @GetMapping("/orderManage") // http://localhost:8080/orderManage/list
    public String listOrders(Model model) {
        List<OrderManage> orders = orderManageMapper.getOrderManageList(); // 调用 XML 中的查询方法
        List<OrderStatus> orderStatuses = orderStatusManageMapper.getOrderStatusList();
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("orders", orders); // 将订单列表传递给前端
        return "orderManage"; // 返回订单管理页面
    }

    /**
     * 根据订单ID查询订单
     */
    @GetMapping("/getOrderById") // http://localhost:8080/orderManage/getOrderById?orderId=1001
    public String getOrderById(@RequestParam("orderId") int orderId, Model model) {
        OrderManage order = orderManageMapper.getOrderManageById(orderId); // 调用 XML 中的查询方法
        OrderStatus orderStatus =orderStatusManageMapper.getOrderStatusByOrderId(orderId);
        order.setOrderStatuses(orderStatus);
        model.addAttribute("order", order); // 将订单信息传递给前端
        return "orderDetail"; // 返回搜索结果
    }

    /**
     * 更新订单
     */
    @PostMapping("/updateOrder")
    public String updateOrder(
            @RequestParam("orderId") int orderId,
            @RequestParam("userId") String userId,
            @RequestParam("orderDate") Date orderDate,
            @RequestParam("totalPrice") double totalPrice,
            @RequestParam("status") OrderStatus status) {
        OrderManage order = new OrderManage();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setOrderDate(orderDate);
        order.setTotalPrice(totalPrice);
        order.setOrderStatuses(status);

        orderManageMapper.updateOrderManage(order); // 调用 XML 中的更新方法
        return "orderDetail"; // 重定向到订单列表页面
    }

    /**
     * 删除订单
     */
    @PostMapping("/deleteOrderManager")
    public String deleteOrderManager(@RequestParam("orderId") int orderId) {
        orderManageMapper.deleteOrderManage(orderId); // 调用 XML 中的删除方法
        orderStatusManageMapper.deleteOrderStatus(orderId);
        lineItemManageMapper.deleteLineItem(orderId);
        return "redirect:/petManage/orderManage";
    }

    /**
     * 根据用户ID查询订单
     */
    @GetMapping("/searchByUserId") // http://localhost:8080/orderManage/searchByUserId?userId=1001
    public String searchByUserId(@RequestParam("userId") int userId, Model model) {
        List<OrderManage> orders = orderManageMapper.searchOrderManageByUserId(userId); // 调用 XML 中的查询方法
        model.addAttribute("orders", orders); // 将查询结果传递给前端
        return "orderDetail"; // 返回订单管理页面
    }


    /**
     * 根据订单状态查询订单
     */
    @GetMapping("/searchByStatus") // http://localhost:8080/orderManage/searchByStatus?status=SHIPPED
    public String searchByStatus(@RequestParam("status") String status, Model model) {
        List<OrderManage> orders = orderManageMapper.searchOrderManageByStatus(status); // 调用 XML 中的查询方法
        model.addAttribute("orders", orders); // 将查询结果传递给前端
        return "orderManage"; // 返回订单管理页面
    }

    /**
     * 更新订单状态
     */
    @GetMapping("/updateOrderStatus")
    public String updateOrderStatus(Model model,@RequestParam("orderId") int orderId) {
        orderStatusManageMapper.updateOrderStatus(orderId,"4"); // 调用 XML 中的更新方法
        List<OrderManage> orders = orderManageMapper.getOrderManageList(); // 调用 XML 中的查询方法
        List<OrderStatus> orderStatuses = orderStatusManageMapper.getOrderStatusList();
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("orders", orders); // 将订单列表传递给前端
        return "orderManage"; // 重定向到订单列表页面
    }


}