package com.example.mypetstore.controller;

import com.example.mypetstore.domain.CategoryManage;
import com.example.mypetstore.domain.ItemManage;
import com.example.mypetstore.domain.ProductManage;
import com.example.mypetstore.persistence.CategoryManageMapper;
import com.example.mypetstore.persistence.ItemManageMapper;
import com.example.mypetstore.persistence.ProductManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//充当get和post
@RequestMapping("/petManage")
public class PetManageController {
    @Autowired
    public CategoryManageMapper categoryMapper;

    @Autowired
    public ProductManageMapper productMapper;

    @Autowired
    public ItemManageMapper itemMapper;
    @Autowired
    private ItemManageMapper itemManageMapper;

    @GetMapping("/categoryManage")//http://localhost:8080/petManage/categoryManage
    public String categoryManage(Model model) {
        List<CategoryManage> categories = categoryMapper.getCategoryManageList();
        model.addAttribute("categories", categories);
        return "categoryManage";
    }

    @PostMapping("/addCategoryManage")
    public String addCategoryManage(@RequestParam("categoryId") String categoryId,
                                    @RequestParam("name") String name,
                                    @RequestParam("description") String description) {
        CategoryManage categoryManage = new CategoryManage();
        categoryManage.setCategoryId(categoryId);
        categoryManage.setName(name);
        categoryManage.setDescription(description);

        // 调用 Mapper 插入数据到数据库
        categoryMapper.addCategoryManage(categoryManage);

        return "redirect:/petManage/categoryManage";  // 重定向到分类管理页面，或者你可以根据需要返回其他页面
    }

    @PostMapping("/editCategoryManage")
    public String editCategoryManage(@RequestParam("categoryId") String categoryId,
                                     @RequestParam("name") String name,
                                     @RequestParam("description") String description) {
        // 创建CategoryManage对象并设置属性
        CategoryManage categoryManage = new CategoryManage();
        categoryManage.setCategoryId(categoryId);
        categoryManage.setName(name);
        categoryManage.setDescription(description);

        // 调用 Mapper 更新数据到数据库
        categoryMapper.updateCategoryManage(categoryManage);

        return "redirect:/petManage/categoryManage";
    }

//    @PostMapping("/deleteCategoryManage")
//    public String deleteCategoryManage(@RequestParam("categoryId") String categoryId,
//                                       @RequestParam("name") String name,
//                                       @RequestParam("description") String description) {
//
//        // 创建CategoryManage对象并设置属性
//        CategoryManage categoryManage = new CategoryManage();
//        categoryManage.setCategoryId(categoryId);
//        categoryManage.setName(name);
//        categoryManage.setDescription(description);
//        categoryMapper.deleteCategoryManage(categoryManage);
//
//            // 如果删除成功，重定向到分类管理页面
//        return "redirect:/petManage/categoryManage";
//    }

    @PostMapping("/deleteCategoryManage")
    public String deleteCategoryManage(@RequestParam("categoryId") String categoryId) {
        categoryMapper.deleteCategoryManage(categoryId);
        return "redirect:/petManage/categoryManage"; // 重定向回分类管理页面
    }


    @GetMapping("/productManage")//http://localhost:8080/petManage/productManage
    public String productManage(Model model) {
        List<ProductManage> products = productMapper.getProductManageList();
        model.addAttribute("products", products);
        return "productManage";
    }

    @PostMapping("/addProductManage")
    public String addProductManage(@RequestParam("productId") String productId,
                                   @RequestParam("categoryId") String categoryId,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description) {
        // 创建 ProductManage 实例并设置属性
        ProductManage productManage = new ProductManage();
        productManage.setProductId(productId);
        productManage.setCategoryId(categoryId);
        productManage.setName(name);
        productManage.setDescription(description);

        // 调用 Mapper 插入数据到数据库
        productMapper.addProductManage(productManage);

        // 重定向到商品管理页面
        return "redirect:/petManage/productManage";
    }

    @PostMapping("/editProductManage")
    public String editProductManage(@RequestParam("productId") String productId,
                                    @RequestParam("categoryId") String categoryId,
                                    @RequestParam("name") String name,
                                    @RequestParam("description") String description) {
        // 创建 ProductManage 对象并设置属性
        ProductManage productManage = new ProductManage();
        productManage.setProductId(productId);
        productManage.setCategoryId(categoryId);
        productManage.setName(name);
        productManage.setDescription(description);

        // 调用 Mapper 更新数据库数据
        productMapper.updateProductManage(productManage);

        // 重定向到商品管理页面
        return "redirect:/petManage/productManage";
    }

    @PostMapping("/deleteProductManage")
    public String deleteProductManage(@RequestParam("productId") String productId) {
        // 调用 Mapper 删除数据库中的商品信息
        productMapper.deleteProductManage(productId);

        // 重定向到商品管理页面
        return "redirect:/petManage/productManage";
    }

    @GetMapping("/itemManage")//http://localhost:8080/petManage/itemManage
    public String itemManage(Model model)
    {
        List<ItemManage> items = itemMapper.getItemManageList();
        model.addAttribute("items", items);
        return "itemManage";
    }

    @PostMapping("/addItemManage")
    public String addItemManage(@RequestParam("itemId") String itemId,
                                @RequestParam("productId") String productId,
                                @RequestParam("listPrice") Double listPrice,
                                @RequestParam("unitCost") Double unitCost,
                                @RequestParam("supplierId") Integer supplierId,
                                @RequestParam("status") String status,
                                @RequestParam("attribute1") String attribute1) {
        // 创建 ItemManage 实例并设置属性
        ItemManage itemManage = new ItemManage();
        itemManage.setItemId(itemId);
        itemManage.setProductId(productId);
        itemManage.setListPrice(listPrice);
        itemManage.setUnitCost(unitCost);
        itemManage.setSupplierId(supplierId);
        itemManage.setStatus(status);
        itemManage.setAttribute1(attribute1);

        // 调用 Mapper 插入数据到数据库
        itemMapper.addItemManage(itemManage);

        // 重定向到商品管理页面
        return "redirect:/petManage/itemManage";
    }

    @PostMapping("/editItemManage")
    public String editItemManage(@RequestParam("itemId") String itemId,
                                   @RequestParam("productId") String productId,
                                   @RequestParam("listPrice") Double listPrice,
                                   @RequestParam("unitCost") Double unitCost,
                                   @RequestParam("supplierId") Integer supplierId,
                                   @RequestParam("status") String status,
                                   @RequestParam("attribute1") String attribute1
//                                 @RequestParam("attribute2") String attribute2,
//                                 @RequestParam("attribute3") String attribute3,
//                                 @RequestParam("attribute4") String attribute4
                                 ) {
        // 创建 ItemManage 实例并设置属性
        ItemManage itemManage = new ItemManage();
        itemManage.setItemId(itemId);
        itemManage.setProductId(productId);
        itemManage.setListPrice(listPrice);
        itemManage.setUnitCost(unitCost);
        itemManage.setSupplierId(supplierId);
        itemManage.setStatus(status);
        itemManage.setAttribute1(attribute1);
//        itemManage.setAttr2(attribute2);
//        itemManage.setAttr3(attribute3);
//        itemManage.setAttr4(attribute4);

        // 调用 Mapper 更新数据
        itemMapper.updateItemManage(itemManage);

        // 重定向到商品管理页面
        return "redirect:/petManage/itemManage";
    }

    @PostMapping("/deleteItemManage")
    public String deleteItemManage(@RequestParam("itemId") String itemId) {
        // 调用 Mapper 删除数据库中的商品项
        itemMapper.deleteItemManage(itemId);

        // 重定向到商品管理页面
        return "redirect:/petManage/itemManage";
    }

    @PostMapping("/updateItemStatus")
    public String updateItemStatus(@RequestParam("itemId") String itemId,
                                   @RequestParam("status") String status) {
        // 调用方法更新商品状态
        itemMapper.updateItemStatus(itemId, status);

        // 重定向到商品管理页面
        return "redirect:/petManage/itemManage";
    }

    @GetMapping("/getItemManageById")//http://localhost:8080/petManage/itemManage
    public String getItemManageById(@RequestParam("itemId") String itemId,Model model)
    {
        List<ItemManage> items = itemMapper.getItemManageById(itemId);
        model.addAttribute("items", items);
        return "itemManage";
    }

    // 新增通过 name 搜索产品的方法
    @GetMapping("/searchProductByName")
    public String searchProductByName(@RequestParam("name") String name, Model model) {
        List<ProductManage> products = productMapper.searchProductManageByName(name);
        model.addAttribute("products", products);
        return "productManage";
    }
}