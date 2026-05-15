package com.brix.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("personal_intro")
public class PersonalIntro {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String welcomeText;

    private String avatar;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
