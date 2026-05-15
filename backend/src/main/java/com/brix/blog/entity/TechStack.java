package com.brix.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("tech_stack")
public class TechStack {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String icon;

    private String description;

    private Integer sortOrder;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
