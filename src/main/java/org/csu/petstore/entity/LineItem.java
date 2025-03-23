package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lineitem")
public class LineItem {
    @TableId(value = "orderid")
    private Integer orderId;
    @TableField(value = "linenum")
    private Integer lineNumber;
    private Integer quantity;
    @TableField(value = "itemid")
    private String itemId;
    @TableField(value = "unitprice")
    private BigDecimal unitPrice;
    //private Item item;
    //private BigDecimal total;
}
