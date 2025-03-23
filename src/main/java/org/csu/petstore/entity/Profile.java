package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("profile")
public class Profile {
    @TableId(value = "userid")
    private String userId;
    @TableField(value = "langpref")
    private String langPref;
    @TableField(value = "favcategory")
    private String favCategory;
    @TableField(value = "mylistopt")
    private int myListOpt;
    @TableField(value = "banneropt")
    private int bannerOpt;
}
