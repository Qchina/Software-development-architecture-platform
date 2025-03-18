package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.CategoryManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
//@Component
public interface CategoryManageMapper {
    List<CategoryManage> getCategoryManageList();
    void addCategoryManage(CategoryManage category);
    void updateCategoryManage(CategoryManage category);
    void deleteCategoryManage(String categoryId);
}


