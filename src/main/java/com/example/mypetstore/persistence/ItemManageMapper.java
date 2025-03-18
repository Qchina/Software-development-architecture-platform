package com.example.mypetstore.persistence;
import com.example.mypetstore.domain.ItemManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ItemManageMapper {
    // 获取所有商品项
    List<ItemManage> getItemManageList();
    // 根据itemId获取单个商品项
    ItemManage getItemManage(String itemId);
    // 新增商品项
    void addItemManage(ItemManage itemManage);
    // 更新商品项
    void updateItemManage(ItemManage itemManage);
    // 删除商品项
    void deleteItemManage(String itemId);
}

