package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@TableName("log")
public class Log {
    @TableId(value = "id")
    private Integer logId;
    private LocalDate logTime;
    @TableField("userName")
    private String username;
    private String title;
    private String text;
}