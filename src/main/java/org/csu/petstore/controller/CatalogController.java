package org.csu.petstore.controller;

import org.csu.petstore.entity.Product;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.vo.CategoryVO;
import org.csu.petstore.vo.ItemVO;
import org.csu.petstore.vo.ProductVO;
import org.csu.petstore.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.alibaba.fastjson.JSON;

@Controller
@Validated
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("index")
    public String index(){
        return "catalog/main";
    }

    @GetMapping("viewCategory")
    public String viewCategory(@RequestParam String categoryId, Model model){
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        model.addAttribute("category", categoryVO);
        return "catalog/category";
    }

    @GetMapping("viewProduct")
    public String viewProduct(@RequestParam String productId, Model model){
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "catalog/product";
    }

    @GetMapping("viewItem")
    public String viewItem(@RequestParam String itemId, Model model){
        ItemVO itemVO = catalogService.getItem(itemId);
        model.addAttribute("item", itemVO);
        return "catalog/item";
    }

    @PostMapping("searchProducts")
    public String searchProducts(@RequestParam String keyword, Model model){
        List<SearchVO> searchVOList = catalogService.getSearch(keyword);

        if (searchVOList.size() != 0) {
            model.addAttribute("searchList", searchVOList);
            return "catalog/searchProducts";
        } else {
            return "catalog/searchNotFound";
        }
    }

    @GetMapping("searchAutoComplete")
    @ResponseBody
    public List<Product> searchAutoComplete(@RequestParam String keyword) {
        // 调用 CatalogService（或 CategoryService）进行产品搜索
        List<Product> productList = catalogService.searchProductList(keyword);

        // 返回产品列表，会自动转换为 JSON 格式
        return productList;
    }


}
