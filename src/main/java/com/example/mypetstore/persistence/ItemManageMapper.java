package com.example.mypetstore.persistence;
import com.example.mypetstore.domain.ItemManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;
@Mapper
public interface ItemManageMapper {
    // 获取所有商品项
    List<ItemManage> getItemManageList();
    // 根据itemId获取单个商品项
    List<ItemManage> getItemManageById(String itemId);
    // 新增商品项
    void addItemManage(ItemManage itemManage);
    // 更新商品项
    void updateItemManage(ItemManage itemManage);
    // 删除商品项
    void deleteItemManage(String itemId);

    // 动态更新商品状态（上架/下架）
    void updateItemStatus(@Param("itemId") String itemId, @Param("status") String status);

    // 根据 productId 和 categoryId 查询 Item
    List<ItemManage> getItemsByProductAndCategory(@Param("productId") String productId, @Param("categoryId") String categoryId);

    // 根据 categoryId 查询 Item
    List<ItemManage> getItemsByCategory(@Param("categoryId") String categoryId);
}

