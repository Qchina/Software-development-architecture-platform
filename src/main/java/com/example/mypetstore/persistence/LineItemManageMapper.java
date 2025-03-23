package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.LineItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LineItemManageMapper {

    // 获取所有订单项
    List<LineItem> getLineItemList();

    // 根据订单ID获取订单项
    List<LineItem> getLineItemByOrderId(String orderId);

    // 添加订单项
    void addLineItem(LineItem lineItem);

    // 更新订单项
    void updateLineItem(LineItem lineItem);

    // 删除订单项
    void deleteLineItem(int orderId);
}