package org.csu.petstore;

import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.Category;
import org.csu.petstore.persistence.CategoryMapper;
import org.csu.petstore.service.AccountService;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.service.impl.AccountServiceImpl;
import org.csu.petstore.vo.AccountVO;
import org.csu.petstore.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyPetStoreSsmDevApplicationTests {

    @Autowired
    private CatalogService catalogService;

    @Test
    void contextLoads() {
        CategoryVO category = catalogService.getCategory("CATS");
        System.out.println(category);
    }

}
