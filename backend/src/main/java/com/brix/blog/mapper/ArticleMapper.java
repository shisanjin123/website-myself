package com.brix.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brix.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT a.*, c.name as category_name FROM article a " +
            "LEFT JOIN category c ON a.category_id = c.id " +
            "WHERE a.is_published = 1 " +
            "ORDER BY a.created_at DESC")
    List<Article> selectPublishedArticles();

    @Select("SELECT a.*, c.name as category_name FROM article a " +
            "LEFT JOIN category c ON a.category_id = c.id " +
            "ORDER BY a.created_at DESC")
    List<Article> selectAllArticles();
}
