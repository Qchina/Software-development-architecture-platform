package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Item;
import org.csu.petstore.entity.LineItem;
import org.csu.petstore.entity.Order;
import org.csu.petstore.entity.OrderStatus;
import org.csu.petstore.service.CartService;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.service.OrderService;
import org.csu.petstore.vo.AccountVO;
import org.csu.petstore.vo.CartItemListMapper;
import org.csu.petstore.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Validated
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private HttpSession session;

    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        Order order = (Order) session.getAttribute("order");
        AccountVO account = (AccountVO) session.getAttribute("account");
        model.addAttribute("loginAccount", account);
        model.addAttribute("order", order);
        session.setAttribute("loginAccount", account);
        return "order/newOrderForm";
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(@ModelAttribute("order") Order order, Model model) {
        AccountVO account = (AccountVO) session.getAttribute("loginAccount");
        LocalDate localDate = LocalDate.now();
        String userId = account.getUsername();
        order.setUsername(userId);
        order.setTotalPrice((BigDecimal) session.getAttribute("subTotal"));
        order.setOrderDate(localDate);
        order.setCourier("m");
        order.setLocale("o");
        orderService.insertOrder(order,account);
        session.setAttribute("order", order);
        cartService.clearCart(userId);
        return "order/confirmOrder";
    }
    @GetMapping("/viewOrder")
    public String viewOrder(Model model) {
        Order order = (Order) session.getAttribute("order");
        CartItemListMapper cartItemListMapper = (CartItemListMapper) session.getAttribute("cartItemListMapper");
        orderService.finishOrder(order,cartItemListMapper);

        int orderId = order.getOrderId();
        List<OrderStatus> orderStatus = orderService.getOrderStatusByOrderId(orderId);
        List<LineItem> lineItemList = orderService.getOrderLineItemByOrderId(orderId);
        List<ItemVO> itemList = new ArrayList<>();
        for (LineItem lineItem : lineItemList) {
            itemList.add(catalogService.getItem(lineItem.getItemId()));
        }

        model.addAttribute("orderStatus", orderStatus);
        session.setAttribute("orderStatus", orderStatus);
        model.addAttribute("orderLineItem", lineItemList);
        session.setAttribute("orderLineItem", lineItemList);
        model.addAttribute("orderItem", itemList);
        session.setAttribute("orderItem", itemList);

        return "order/viewOrderForm";
    }
    @GetMapping("/listOrder")
    public String listOrder(String username,Model model) {
        List<Order> orderList = orderService.getOrdersByUsername(username);
        model.addAttribute("orderList", orderList);
        return "order/myOrders";
    }

    @GetMapping("/viewTheOrder")
    public String viewTheOrder(@RequestParam int orderId, Model model) {
        Order order = orderService.getOrderByOrderId(orderId);
        List<OrderStatus> orderStatus = orderService.getOrderStatusByOrderId(orderId);
        List<LineItem> lineItemList = orderService.getOrderLineItemByOrderId(orderId);
        List<ItemVO> itemList = new ArrayList<>();
        for (LineItem lineItem : lineItemList) {
            itemList.add(catalogService.getItem(lineItem.getItemId()));
        }

        model.addAttribute("theOrder", order);
        session.setAttribute("theOrder", order);
        model.addAttribute("theOrderStatus", orderStatus);
        session.setAttribute("theOrderStatus", orderStatus);
        model.addAttribute("theOrderLineItem", lineItemList);
        session.setAttribute("theOrderLineItem", lineItemList);
        model.addAttribute("theOrderItem", itemList);
        session.setAttribute("theOrderItem", itemList);

        return "order/lookTheOrder";
    }

}

