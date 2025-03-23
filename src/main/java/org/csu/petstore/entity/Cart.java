package org.csu.petstore.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("cart")
public class Cart {
    @TableId("username")
    private String username;
    @TableField("itemid")
    private String itemId;
    @TableField("quantity")
    private Integer quantity;
    @TableField("productid")
    private String productId;
    private String description;
    private int in_stock;
    @TableField("listprice")
    private BigDecimal listPrice;
    @TableField("totalcost")
    private BigDecimal totalCost;
    @TableField("isRemoved")
    private int isRemoved;

}