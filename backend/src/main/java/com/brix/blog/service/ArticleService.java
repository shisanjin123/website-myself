package com.brix.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brix.blog.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<Article> getPublishedArticles();

    List<Article> getAllArticles();

    Article getArticleDetail(Long id);
}
