package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.ProductManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManageMapper {
    List<ProductManage> getProductManageList();
    void addProductManage(ProductManage product);
    void updateProductManage(ProductManage product);
    void deleteProductManage(String productId);
}