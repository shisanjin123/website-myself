package com.brix.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brix.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
