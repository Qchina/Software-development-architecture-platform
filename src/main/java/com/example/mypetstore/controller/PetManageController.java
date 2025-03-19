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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

//    @GetMapping("/productManage")
//    public String productManage(@RequestParam(value = "categoryId", required = false) String categoryId, Model model) {
//        List<ProductManage> products;
//
//        if (categoryId == null || categoryId.equals("null")) {
//            products = productMapper.getProductManageList();
//        } else {
//            products = productMapper.getProductManageListByCategory(categoryId);
//        }
//
//        // 提取唯一 categoryId
//        Set<String> uniqueCategories = products.stream()
//                .map(ProductManage::getCategoryId)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toSet());
//
//        model.addAttribute("products", products);
//        model.addAttribute("categories", uniqueCategories);
//        model.addAttribute("selectedCategoryId", categoryId); // 传递当前选择的 categoryId
//
//        return "productManage";
//    }

    @GetMapping("/productManage")
    public String productManage(@RequestParam(value = "categoryId", required = false) String categoryId, Model model) {
        // 获取商品列表，根据传入的 categoryId 过滤
        List<ProductManage> products;
        if (categoryId == null || categoryId.equals("null")) {
            // 没有选择分类时获取所有商品
            products = productMapper.getProductManageList();
        } else {
            // 如果有选择分类，根据 categoryId 获取商品
            products = productMapper.getProductManageListByCategory(categoryId);
        }

        // 获取所有的商品分类
        List<CategoryManage> categories = categoryMapper.getCategoryManageList();

        // 将数据添加到模型中
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId); // 传递当前选择的 categoryId，以便前端标记选中项

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

    @GetMapping("/itemManage")
    public String itemManage(
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "productIdCategoryId", required = false) String productIdCategoryId,
            Model model) {

        List<ItemManage> items;

        // 如果没有选择筛选条件，返回所有项
        if ((categoryId == null || categoryId.equals("null")) &&
                (productIdCategoryId == null || productIdCategoryId.equals("null"))) {
            items = itemMapper.getItemManageList();
        } else {
            // 根据 categoryId 或 productIdCategoryId 进行筛选
            if (productIdCategoryId != null && !productIdCategoryId.equals("null")) {
                // 解析出 productId 和 categoryId
                String[] parts = productIdCategoryId.split("_");
                String productId = parts[0];
                String categoryIdFromProduct = parts[1];

                // 使用 productId 和 categoryId 进行筛选
                items = itemMapper.getItemsByProductAndCategory(productId, categoryIdFromProduct);
            } else if (categoryId != null && !categoryId.equals("null")) {
                // 只根据 categoryId 进行筛选
                items = itemMapper.getItemsByCategory(categoryId);
            } else {
                items = itemMapper.getItemManageList();
            }
        }

        // 获取所有产品信息和类别信息，用于下拉框选择
        List<ProductManage> products = productMapper.getProductManageList();
        List<CategoryManage> categories = categoryMapper.getCategoryManageList();
        model.addAttribute("items", items);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "itemManage";  // 返回 itemManage 页面
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